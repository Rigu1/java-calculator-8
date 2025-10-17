package calculator.domain;

import java.util.Arrays;

public class Calculator {
    private final Operands operands;

    public Calculator(Operands operands) {
        this.operands = operands;
    }

    public long add() {
        return operands.getOperands()
                .stream()
                .mapToLong(Operand::getValue)
                .sum();
    }
}
