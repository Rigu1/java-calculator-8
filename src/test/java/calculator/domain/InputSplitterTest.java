package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputSplitterTest {

    @Test
    void 헤더와_표현식_분리하기() {
        String inputText = "//;\\n1;2,3";

        InputSplitter inputSplitter = InputSplitter.from(inputText);

        assertThat(inputSplitter.getHeader()).isEqualTo("//;\\n");
        assertThat(inputSplitter.getExpression()).isEqualTo("1;2,3");
    }

    @Test
    void 헤더가_없는_경우() {
        String inputText = "1;2,3";

        InputSplitter inputSplitter = InputSplitter.from(inputText);

        assertThat(inputSplitter.getHeader()).isEqualTo(null);
        assertThat(inputSplitter.getExpression()).isEqualTo("1;2,3");
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;1;2,3", "//;\n1,2", "//;\\1,2"})
    void 헤더_접미사가_없는_경우_예외_발생(String inputText) {
        assertThatThrownBy(() -> InputSplitter.from(inputText))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
