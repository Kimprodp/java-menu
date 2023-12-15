package menu.view;

import java.util.Arrays;
import java.util.List;
import menu.dto.CoachNameDto;
import menu.dto.MenuNameDto;

public class InputProcessor {

    private static final int MIN_NUMBER_OF_COACH = 2;
    private static final int MAX_NUMBER_OF_COACH = 5;
    private static final int MAX_NUMBER_OF_EXCLUDE_MENU = 2;
    private static final String SEPARATOR = ",";

    private static final String ERROR_NUMBER_OF_COACH = "코치는 최소 2명 이상, 최대 5명까지 입력해주세요.";
    private static final String ERROR_NUMBER_OF_EXCLUDE_MENU = "못먹는 메뉴는 최대 2개까지 입력해주세요.";

    public static CoachNameDto convertCoachName(String input) {
        List<String> coachNames = validateNumberOfCoach(input);
        return new CoachNameDto(coachNames);

    }

    public static MenuNameDto convertExcludeMenu(String input) {
        List<String> excludeMenu = validateNumberOfExcludeMenu(input);
        return new MenuNameDto(excludeMenu);
    }

    private static List<String> validateNumberOfCoach(String input) {
        List<String> coachName = Arrays.asList(input.split(SEPARATOR));
        if (coachName.size() < MIN_NUMBER_OF_COACH || coachName.size() > MAX_NUMBER_OF_COACH) {
            throw new IllegalArgumentException(ERROR_NUMBER_OF_COACH);
        }
        return coachName;
    }

    private static List<String> validateNumberOfExcludeMenu(String input) {
        List<String> excludeMenu = Arrays.asList(input.split(SEPARATOR));
        if (excludeMenu.size() >  MAX_NUMBER_OF_EXCLUDE_MENU) {
            throw new IllegalArgumentException(ERROR_NUMBER_OF_EXCLUDE_MENU);
        }
        return excludeMenu;
    }
}
