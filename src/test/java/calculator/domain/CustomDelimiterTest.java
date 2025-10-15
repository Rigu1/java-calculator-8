package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CustomDelimiterTest {

    @Test
    void 커스텀_구분자_추출하기() {
        String inputText = "//;\\n1;2";

        CustomDelimiter customDelimiter = new CustomDelimiter(inputText);

        assertThat(customDelimiter.getCustomDelimiter()).isEqualTo(";");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;n1;2", "///;\\n1", "//a\\\\n2"})
    void 정해진_포맷이_아니라면_예외_발생(String inputText) {
        assertThatThrownBy(() -> new CustomDelimiter(inputText))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//,\\n1:2", "//:\\n1:2"})
    void 기본_구분자와_중복된다면_예외_발생(String inputText) {
        assertThatThrownBy(() -> new CustomDelimiter(inputText))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
