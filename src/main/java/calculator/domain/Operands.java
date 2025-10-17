package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class Operands {
    List<Operand> operands;

    private Operands(List<Operand> operands) {
        this.operands = operands;
    }

    public static Operands of(List<String> delimiter, String expression) {
        List<Operand> operands = splitExpressionByDelimiter(delimiter, expression);
        return new Operands(operands);
    }

    private static List<Operand> splitExpressionByDelimiter(List<String> delimiter, String expression) {
        String[] numbers = expression.split(String.join("|", delimiter));

        return Arrays.stream(numbers)
                .map(Operands::parseAndValidate)
                .map(Operand::new)
                .toList();
    }

    public List<Operand> getOperands() {
        return operands;
    }

    private static int parseAndValidate(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
