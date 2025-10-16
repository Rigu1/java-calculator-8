package calculator.parser;

public class CustomDelimiterParser {

    public static String parse(String headerOfCustomDelimiter) {
        validate(headerOfCustomDelimiter);
        return headerOfCustomDelimiter.substring(2, headerOfCustomDelimiter.indexOf("\\n"));
    }

    private static void validate(String headerOfCustomDelimiter) {
        if (!headerOfCustomDelimiter.startsWith("//")) {
            throw new IllegalArgumentException();
        }

        if (headerOfCustomDelimiter.indexOf("\\n") != 3) {
            throw new IllegalArgumentException();
        }
    }
}
