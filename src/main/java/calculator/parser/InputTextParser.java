package calculator.parser;

public class InputTextParser {

    public static String parse(String expression) {
        validate(expression);
        return expression.substring(2, expression.indexOf("\\n"));
    }

    private static void validate(String expression) {
        if (!expression.startsWith("//")) {
            throw new IllegalArgumentException();
        }

        if (expression.indexOf("\\n") != 3) {
            throw new IllegalArgumentException();
        }
    }
}
