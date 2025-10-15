package calculator;

public class CustomDelimiter {
    private final String customDelimiter;

    public CustomDelimiter(String inputText) {
        String customDelimiter = parse(inputText);
        validateCustomDelimiter(customDelimiter);
        this.customDelimiter = customDelimiter;
    }

    public String getCustomDelimiter() {
        return customDelimiter;
    }

    public String parse(String expression) {
        validateExpression(expression);
        return expression.substring(2, expression.indexOf("\\n"));
    }

    private void validateExpression(String expression) {
        if (!expression.startsWith("//")) {
            throw new IllegalArgumentException();
        }

        if (expression.indexOf("\\n") != 3) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.equals(",") || customDelimiter.equals(":")) {
            throw new IllegalArgumentException();
        }
    }
}
