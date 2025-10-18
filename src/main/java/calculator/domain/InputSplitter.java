package calculator.domain;

public class InputSplitter {
    private static final String HEADER_PREFIX = "//";
    private static final String HEADER_SUFFIX = "\\n";
    private static final int HEADER_SUFFIX_LENGTH = 2;


    private final String header;
    private final String expression;

    private InputSplitter(String header, String expression) {
        this.header = header;
        this.expression = expression;
    }

    public static InputSplitter from(String inputText) {
        if (inputText.startsWith(HEADER_PREFIX)) {
            return InputSplitter.create(inputText);
        }
        return new InputSplitter(null, inputText);
    }

    private static InputSplitter create(String inputText) {
        int headerSuffixIndex = inputText.indexOf(HEADER_SUFFIX);
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

    private static String extractHeader(String inputText, int headerSuffixIndex) {
        return inputText.substring(0, headerSuffixIndex + HEADER_SUFFIX_LENGTH);
    }

    private static String extractExpression(String inputText, int headerSuffixIndex) {
        return inputText.substring(headerSuffixIndex + HEADER_SUFFIX_LENGTH);
    }

    private static void validateSuffixIndex(int suffixIndex) {
        if (suffixIndex < 0) {
            throw new IllegalArgumentException();
        }
    }
}
