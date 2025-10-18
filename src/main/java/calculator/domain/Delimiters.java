package calculator.domain;

import java.util.List;
import java.util.stream.Stream;

public class Delimiters {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");
    private final List<String> delimiters;

    public Delimiters(List<String> delimiters) {
        this.delimiters = delimiters;
    }

    public static Delimiters from(String headerOfCustomDelimiter) {
        if (headerOfCustomDelimiter != null) {
            validateHeaderFormat(headerOfCustomDelimiter);
            String customDelimiter = headerOfCustomDelimiter.substring(2, headerOfCustomDelimiter.indexOf("\\n"));
            validateDuplicateDefaultDelimiter(customDelimiter);

            return new Delimiters(Stream.concat(DEFAULT_DELIMITERS.stream(), Stream.of(customDelimiter)).toList());
        }
        return new Delimiters(DEFAULT_DELIMITERS);
    }

    public List<String> getDelimiters() {
        return this.delimiters;
    }

    private static void validateHeaderFormat(String headerOfCustomDelimiter) {
        if (headerOfCustomDelimiter.indexOf("\\n") != 3) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDuplicateDefaultDelimiter (String customDelimiter) {
        if (DEFAULT_DELIMITERS.contains(customDelimiter)) {
            throw new IllegalArgumentException();
        }
    }
}

