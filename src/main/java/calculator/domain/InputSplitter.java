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
            return InputSplitter.create(inputText);
        }
        return new InputSplitter(null, inputText);
    }

    private static InputSplitter create(String inputText) {
        int headerSuffixIndex = inputText.indexOf("\\n");
        validateSuffixIndex(headerSuffixIndex);

        return new InputSplitter(
                extractHeader(inputText, headerSuffixIndex),
                extractExpression(inputText, headerSuffixIndex)
        );
    }

    public String getHeader() {
        return header;
    }

    public String getExpression() {
        return expression;
    }

    private static String extractHeader(String inputText, int index) {
        return inputText.substring(0, index + 2);
    }

    private static String extractExpression(String inputText, int index) {
        return inputText.substring(index + 2);
    }

    private static void validateSuffixIndex(int suffixIndex) {
        if (suffixIndex < 0) {
            throw new IllegalArgumentException();
        }
    }
}
