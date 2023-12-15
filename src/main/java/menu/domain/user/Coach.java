package menu.domain.user;

import java.util.List;
import menu.domain.service.Menu;

public class Coach {

    private static final String ERROR_COACH_NAME = "코치의 이름은 2~4글자로 입력해주세요.";
    private static final int MIN_COACH_NAME_LENGTH = 2;
    private static final int MAX_COACH_NAME_LENGTH = 4;

    private String name;
    private List<Menu> menuToExclude;

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getMenuToExclude() {
        return menuToExclude;
    }

    public void addMenuToExclude(List<Menu> menuToExclude) {
        this.menuToExclude = menuToExclude;
    }

    private String validateName(String input) {
        if (input.length() < MIN_COACH_NAME_LENGTH || input.length() > MAX_COACH_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_COACH_NAME);
        }
        return input;
    }
}
