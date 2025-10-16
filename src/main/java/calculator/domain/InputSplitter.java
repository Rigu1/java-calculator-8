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
            int suffixIndex = inputText.indexOf("\\n");
            validateSuffixIndex(suffixIndex);

            return new InputSplitter(inputText.substring(0, suffixIndex + 2),  inputText.substring(suffixIndex + 2));
        }
        return new InputSplitter(null, inputText);
    }

    public String getHeader() {
        return header;
    }

    public String getExpression() {
        return expression;
    }

    private static void validateSuffixIndex(int suffixIndex) {
        if (suffixIndex < 0) {
            throw new IllegalArgumentException();
        }
    }
}
