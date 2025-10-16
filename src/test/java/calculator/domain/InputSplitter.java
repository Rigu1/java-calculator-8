package calculator.domain;

public class InputSplitter {
    private final String header;
    private final String expression;

    private InputSplitter(String header, String expression) {
        this.header = header;
        this.expression = expression;
    }

    public static InputSplitter from(String inputText) {
        if (inputText.startsWith("//")) {
            return new InputSplitter(inputText.substring(0, 5),  inputText.substring(5));
        }
        return new InputSplitter(null, inputText);
    }

    public String getHeader() {
        return header;
    }

    public String getExpression() {
        return expression;
    }

}
