package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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

        assertThat(inputSplitter.getHeader()).isEqualTo("");
        assertThat(inputSplitter.getExpression()).isEqualTo("1;2,3");
    }
}
