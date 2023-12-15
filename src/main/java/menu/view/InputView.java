package menu.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import menu.dto.CoachNameDto;
import menu.dto.MenuNameDto;

public class InputView {

    private static final String INPUT_COACH_NAME = "\n"+ "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String INPUT_EXCLUDE_MENU = "\n"+ "%s(이)가 못 먹는 메뉴를 입력해 주세요.";

    public static CoachNameDto readCoachName() {
        System.out.println(INPUT_COACH_NAME);
        String input = Console.readLine();
        return InputProcessor.convertCoachName(input);
    }

    public static MenuNameDto readExcludeMenu(String name) {
        System.out.println(String.format(INPUT_EXCLUDE_MENU, name));
        String input = Console.readLine();
        return InputProcessor.convertExcludeMenu(input);
    }
}
