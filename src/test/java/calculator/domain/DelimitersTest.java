package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DelimitersTest {

    @Test
    void 커스텀_구분자_추출하기() {
        String headerOfCustomDelimiter = "//;\\n";

        Delimiters delimiters = Delimiters.from(headerOfCustomDelimiter);

        assertThat(delimiters.toRegex()).isEqualTo(",|:|;");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//,\\n", "//:\\n"})
    void 기본_구분자와_중복된다면_예외_발생(String headerOfCustomDelimiter) {
        assertThatThrownBy(() -> Delimiters.from(headerOfCustomDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"///;\\n", "//[\\\\n", "//////;\\n"})
    void 정해진_포맷이_아니라면_예외_발생(String headerOfCustomDelimiter) {
        assertThatThrownBy(() -> Delimiters.from(headerOfCustomDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구분자가_문자가_아니라면_예외_발생() {
        String headerOfCustomDelimiter = "//ads\\n";

        assertThatThrownBy(() -> Delimiters.from(headerOfCustomDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구분자가_빈_문자열이라면_예외_발생() {
        String headerOfCustomDelimiter = "//\\n";

        assertThatThrownBy(() -> Delimiters.from(headerOfCustomDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
