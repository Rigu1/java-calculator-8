package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class Operands {
    private static final String REGEX_DELIMITER = "|";

    List<Operand> operands;

    private Operands(List<Operand> operands) {
        this.operands = operands;
    }

    public static Operands of(String regexOfDelimiters, String expression) {
        return new Operands(parseOperands(regexOfDelimiters, expression));
    }

    private static List<Operand> parseOperands(String regexDelimiters, String expression) {
        String[] expressionElements = splitExpressionByDelimiters(regexDelimiters, expression);

        return Arrays.stream(expressionElements)
                .filter(element -> !element.isBlank())
                .map(Operand::from)
                .toList();
    }

    public List<Operand> getOperands() {
        return operands;
    }

    private static String[] splitExpressionByDelimiters(String delimiters, String expression) {
        return expression.split(delimiters);
    }
}
