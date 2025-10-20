package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void 피연산자들의_합계_계산하기() {
        String regexOfDelimiters = ",|:";
        String expression = "1,2:3" ;
        Operands operands = Operands.of(regexOfDelimiters, expression);

        Calculator calculator = new Calculator(operands);

        assertThat(calculator.add()).isEqualTo(6L);
    }

    @Test
    void 빈_입력이_들어온_경우() {
        String regexOfDelimiters = ",|:";
        String expression = "";
        Operands operands = Operands.of(regexOfDelimiters, expression);

        Calculator calculator = new Calculator(operands);

        assertThat(calculator.add()).isEqualTo(0L);
    }
}
