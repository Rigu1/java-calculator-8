package calculator.domain;

public class Operand {
    private final int value;

    private Operand(int value) {
        this.value = value;
    }

    public static Operand from(String element) {
        int value = tryParseInt(element);
        validatePositive(value);

        return new Operand(value);
    }

    public int getValue() {
        return value;
    }

    private static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
