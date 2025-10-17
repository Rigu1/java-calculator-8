package calculator.ui;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String readLine() {
        System.out.print("덧셈할 문자열을 입력해 주세요.\n");
        return Console.readLine();
    }
}
