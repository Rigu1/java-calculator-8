package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class Operands {
    private static final String REGEX_DELIMITER = "|";

    List<Operand> operands;

    private Operands(List<Operand> operands) {
        this.operands = operands;
    }

    public static Operands of(List<String> delimiter, String expression) {
        return new Operands(parseOperands(delimiter, expression));
    }

    private static List<Operand> parseOperands(List<String> delimiter, String expression) {
        String[] expressionElements = splitExpressionByDelimiter(delimiter, expression);

        return Arrays.stream(expressionElements)
                .filter(element -> !element.isBlank())
                .map(Operand::from)
                .toList();
    }

    public List<Operand> getOperands() {
        return operands;
    }

    private static String[] splitExpressionByDelimiter(List<String> delimiter, String expression) {
        return expression.split(String.join(REGEX_DELIMITER, delimiter));
    }


}
