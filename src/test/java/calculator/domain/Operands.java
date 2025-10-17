package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class Operands {
    List<Integer> operands;

    private Operands(List<Integer> operands) {
        this.operands = operands;
    }

    public static Operands of(List<String> delimiter, String expression) {
        List<Integer> operands = splitExpressionByDelimiter(delimiter, expression);
        return new Operands(operands);
    }

    private static List<Integer> splitExpressionByDelimiter(List<String> delimiter, String expression) {
        String[] numbers = expression.split(String.join("|", delimiter));

        return Arrays.stream(numbers)
                .map(Operands::parseAndValidate)
                .toList();
    }

    public List<Integer> getOperands() {
        return operands;
    }

    private static int parseAndValidate(String value) {
        int num;

        try {
            num = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        if (num <= 0) {
            throw new IllegalArgumentException();
        }

        return num;
    }
}
