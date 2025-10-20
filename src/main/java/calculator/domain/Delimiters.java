package calculator.domain;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Delimiters {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");
    private static final String HEADER_SUFFIX = "\\n";
    private static final int CUSTOM_DELIMITER_INDEX = 2;
    private static final int HEADER_SUFFIX_INDEX = 3;


    private final List<String> delimiters;

    public Delimiters(List<String> delimiters) {
        this.delimiters = delimiters;
    }

    public static Delimiters from(String headerOfCustomDelimiter) {
        if (headerOfCustomDelimiter != null) {
            validateHeaderFormat(headerOfCustomDelimiter);

            return Delimiters.create(headerOfCustomDelimiter);
        }
        return new Delimiters(DEFAULT_DELIMITERS);
    }

    private static Delimiters create(String headerOfCustomDelimiter) {
        String customDelimiter = extractCustomDelimiterByHeader(headerOfCustomDelimiter);
        validateDuplicateDefaultDelimiter(customDelimiter);

        return new Delimiters(Stream.concat(DEFAULT_DELIMITERS.stream(), Stream.of(customDelimiter)).toList());
    }

    public String toRegex() {
        return delimiters.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

    private static String extractCustomDelimiterByHeader(String headerOfCustomDelimiter) {
        return headerOfCustomDelimiter.substring(CUSTOM_DELIMITER_INDEX, headerOfCustomDelimiter.indexOf(HEADER_SUFFIX));
    }

    private static void validateHeaderFormat(String headerOfCustomDelimiter) {
        if (headerOfCustomDelimiter.indexOf(HEADER_SUFFIX) != HEADER_SUFFIX_INDEX) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDuplicateDefaultDelimiter (String customDelimiter) {
        if (DEFAULT_DELIMITERS.contains(customDelimiter)) {
            throw new IllegalArgumentException();
        }
    }
}

