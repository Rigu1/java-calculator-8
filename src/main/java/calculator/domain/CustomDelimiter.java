package calculator.domain;

import static calculator.parser.CustomDelimiterParser.parse;

public class CustomDelimiter {
    private final String customDelimiter;

    public CustomDelimiter(String inputText) {
        String customDelimiter = parse(inputText);
        validate(customDelimiter);
        this.customDelimiter = customDelimiter;
    }

    public String getCustomDelimiter() {
        return customDelimiter;
    }

    private void validate(String customDelimiter) {
        if (customDelimiter.equals(",") || customDelimiter.equals(":")) {
            throw new IllegalArgumentException();
        }
    }
}
