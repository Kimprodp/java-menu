package menu.dto;

import java.util.List;

public class MenuNameDto {

    private List<String> MenuName;

    public MenuNameDto(List<String> menuName) {
        MenuName = menuName;
    }

    public List<String> getMenuName() {
        return MenuName;
    }
}
