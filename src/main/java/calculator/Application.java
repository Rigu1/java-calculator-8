package calculator;

import calculator.domain.Calculator;
import calculator.domain.Delimiters;
import calculator.domain.InputSplitter;
import calculator.domain.Operands;
import calculator.ui.InputView;
import calculator.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputSplitter inputSplitter = InputSplitter.from(InputView.readLine());
        Delimiters delimiters = Delimiters.from(inputSplitter.getHeader());
        Operands operands = Operands.of(delimiters.getDelimiters(), inputSplitter.getExpression());

        Calculator calculator = new Calculator(operands);

        OutputView.printResult(calculator.add());
    }
}
