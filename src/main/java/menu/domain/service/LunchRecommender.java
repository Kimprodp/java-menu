package menu.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import menu.domain.constants.Category;
import menu.domain.constants.DayOfWeek;

public class LunchRecommender {

    private static final int MAX_COUNT_CATEGORY_DUPLICATES = 2;

    public RecommendedResults recommendLunch(List<DayOfWeek> dateOfUse, LinkedHashMap<String, List<Menu>> userInfo) {
        List<Category> categories = recommendCategory(dateOfUse.size());
        LinkedHashMap<String, List<Menu>> result = new LinkedHashMap<>();

        for (String user : userInfo.keySet()) {
            List<Menu> menus = recommendMenu(categories, userInfo.get(user));
            result.put(user, menus);
        }

        return new RecommendedResults(dateOfUse, categories, result);
    }

    private List<Category> recommendCategory(int count) {
        List<Category> categories = new ArrayList<>();
        while (categories.size() != count) {
            Category category = Category.getRandomCategory();
            if (isUnderMaxCategory(categories, category)) {
                categories.add(category);
            }
        }
        return categories;
    }

    private List<Menu> recommendMenu(List<Category> categories, List<Menu> menusToExclude) {
        List<Menu> menus = new ArrayList<>();
        String menuName;
        for (Category category : categories) {
            do {
                menuName = category.getRandomMenu();
            } while (haveSameMenu(menus, menuName) || isExcludeMenu(menusToExclude, menuName));
            menus.add(new Menu(menuName));
        }
        return menus;
    }

    private boolean isUnderMaxCategory(List<Category> categories, Category targetCategory) {
        int numberOfInclusions = (int) categories.stream()
                .filter(category -> category.getCategoryName().equals(targetCategory.getCategoryName()))
                .count();

        return numberOfInclusions < MAX_COUNT_CATEGORY_DUPLICATES;
    }

    private boolean haveSameMenu(List<Menu> menus, String targetMenu) {
        return menus.stream()
                .anyMatch(menu -> menu.getName().equals(targetMenu));
    }

    private boolean isExcludeMenu(List<Menu> menusToExclude, String targetMenu) {
        return menusToExclude.stream()
                .anyMatch(menu -> menu.getName().equals(targetMenu));
    }
}
