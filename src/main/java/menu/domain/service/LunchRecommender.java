package menu.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.constants.Category;
import menu.domain.constants.DayOfWeek;

public class LunchRecommender {

    private static final int MAX_COUNT_CATEGORY_DUPLICATES = 2;

    private RecommendedResults recommendedResults;

    public RecommendedResults recommendLunch(List<DayOfWeek> dateOfUse, LinkedHashMap<String, List<Menu>> userInfo) {
        recommendedResults = new RecommendedResults(dateOfUse, new ArrayList<>(userInfo.keySet()));

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < dateOfUse.size(); i++) {
            Category category = recommendCategory(categories);
            categories.add(category);

            for (String user : userInfo.keySet()) {
                Menu menu = recommendMenu(category, recommendedResults.getUserMenu(user), userInfo.get(user));
                recommendedResults.addMenu(user, menu);
            }
        }
        recommendedResults.addCategory(categories);
        return recommendedResults;
    }

    private Category recommendCategory(List<Category> categories) {
        Category category;
        do {
            category = Category.getRandomCategory();
        } while (!isAvailableCategory(categories, category));
        return category;
    }

    private Menu recommendMenu(Category category, List<Menu> userRecommendMenu, List<Menu> menusToExclude) {
        String menuName;
        do {
            menuName = category.getRandomMenu();
        } while (haveSameMenu(userRecommendMenu, menuName) || isExcludeMenu(menusToExclude, menuName));
        return new Menu(menuName);
    }

    private boolean isAvailableCategory(List<Category> categories, Category targetCategory) {
        int numberOfInclusions = (int) categories.stream()
                .filter(category -> category.getCategoryName().equals(targetCategory.getCategoryName()))
                .count();

        return numberOfInclusions < MAX_COUNT_CATEGORY_DUPLICATES;
    }

    private boolean haveSameMenu(List<Menu> userRecommendMenu, String targetMenu) {
        return userRecommendMenu.stream()
                .anyMatch(menu -> menu.getName().equals(targetMenu));
    }

    private boolean isExcludeMenu(List<Menu> menusToExclude, String targetMenu) {
        return menusToExclude.stream()
                .anyMatch(menu -> menu.getName().equals(targetMenu));
    }
}
