package calculator.domain;

public class Operand {
    private final int value;

    public Operand(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
