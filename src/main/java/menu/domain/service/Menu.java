package menu.domain.service;

import java.util.Arrays;
import menu.domain.constants.Category;

public class Menu {

    private static final String ERROR_EXCLUDE_MENU_NAME = "메뉴명이 올바르지 않습니다.";

    private String name;

    public Menu(String name) {
        this.name = validateMenuName(name);
    }

    public String getName() {
        return name;
    }

    private String validateMenuName(String input) {
        if (Arrays.stream(Category.values())
                .map(Category::getMenu)
                .noneMatch(menus -> menus.contains(input))) {
            throw new IllegalArgumentException(ERROR_EXCLUDE_MENU_NAME);
        }
        return input;
    }
}
