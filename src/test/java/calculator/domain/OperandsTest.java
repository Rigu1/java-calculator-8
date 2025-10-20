package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class OperandsTest {

    @Test
    void 표현식에서_피연산자_추출하기() {
        String regexOfDelimiters = ",|:";
        String expression = "1,2:3" ;

        Operands operands = Operands.of(regexOfDelimiters, expression);

        assertThat(operands.getOperands())
                .extracting("value")
                .containsExactly(1, 2, 3);
    }

    @Test
    void 빈_토큰_입력() {
        String regexOfDelimiters = ",|:";
        String expression = "1,:3";

        Operands operands = Operands.of(regexOfDelimiters, expression);

        assertThat(operands.getOperands())
                .extracting("value")
                .containsExactly(1, 3);
    }

    @Test
    void 양수가_아닌_값_입력_시_예외_발생() {
        String regexOfDelimiters = ",|:";
        String expression = "-1,2:3";

        assertThatThrownBy(() -> Operands.of(regexOfDelimiters, expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구분자를_제외한_문자_입력_시_예외_발생() {
        String regexOfDelimiters = ",|:";
        String expression = "1,;3";

        assertThatThrownBy(() -> Operands.of(regexOfDelimiters, expression))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
