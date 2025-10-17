package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void 피연산자들의_합계_계산하기() {
        List<String> delimiter = List.of(",", ":");
        String expression = "1,2:3" ;
        Operands operands = Operands.of(delimiter, expression);

        Calculator calculator = new Calculator(operands);

        assertThat(calculator.add()).isEqualTo(6L);
    }
}
