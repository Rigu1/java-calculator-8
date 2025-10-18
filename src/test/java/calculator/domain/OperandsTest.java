package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class OperandsTest {

    @Test
    void 표현식에서_피연산자_추출하기() {
        List<String> delimiter = List.of(",", ":");
        String expression = "1,2:3" ;

        Operands operands = Operands.of(delimiter, expression);

        assertThat(operands.getOperands())
                .extracting("value")
                .containsExactly(1, 2, 3);
    }

    @Test
    void 빈_토큰_발생_시_예외() {
        List<String> delimiter = List.of(",", ":");
        String expression = "1,:3";

        assertThatThrownBy(() -> Operands.of(delimiter, expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 양수가_아닌_값_입력_시_예외_발생() {
        List<String> delimiter = List.of(",", ":");
        String expression = "-1,2:3";

        assertThatThrownBy(() -> Operands.of(delimiter, expression))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구분자를_제외한_문자_입력_시_예외_발생() {
        List<String> delimiter = List.of(",", ":");
        String expression = "1,:3";

        assertThatThrownBy(() -> Operands.of(delimiter, expression))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
