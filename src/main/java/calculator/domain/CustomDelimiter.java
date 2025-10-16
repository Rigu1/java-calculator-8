package calculator.domain;

public class CustomDelimiter {
    private final String customDelimiter;

    public CustomDelimiter(String customDelimiter) {
        this.customDelimiter = customDelimiter;
    }

    public static CustomDelimiter from(String headerOfCustomDelimiter) {
        validateHeader(headerOfCustomDelimiter);
        String customDelimiter = headerOfCustomDelimiter.substring(2, headerOfCustomDelimiter.indexOf("\\n"));
        validateCustomDelimiter(customDelimiter);
        return new CustomDelimiter(customDelimiter);
    }

    public String getCustomDelimiter() {
        return customDelimiter;
    }

    private static void validateHeader(String headerOfCustomDelimiter) {
        if (headerOfCustomDelimiter.indexOf("\\n") != 3) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.equals(",") || customDelimiter.equals(":")) {
            throw new IllegalArgumentException();
        }
    }


}

