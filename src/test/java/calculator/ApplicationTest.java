package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 빈_입력이_들어온_경우() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 커스텀_구분자_미사용() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    @DisplayName("split() 사용 방식에 따른 커스텀 구분자로 대괄호 특수문자 사용 테스트")
    void 대괄호_커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//[\\n1[2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"//,n1,2", "//,1,2"})
    void 헤더_접미사_없는_경우_예외_테스트(String inputText) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(inputText))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_문자열인_경우_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//asv\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_비어있는_경우_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 구분자_중복_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//,\\n1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 음수_입력_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 구분자를_제외한_문자_입력_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1,2a3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
