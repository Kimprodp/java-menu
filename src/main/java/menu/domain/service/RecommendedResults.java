package menu.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import menu.domain.constants.Category;
import menu.domain.constants.DayOfWeek;

public class RecommendedResults {

    private List<DayOfWeek> dateOfUse;
    private List<Category> categories;
    private LinkedHashMap<String, List<Menu>> recommendedResult;

    public RecommendedResults(List<DayOfWeek> dateOfUse, List<String> users) {
        this.dateOfUse = dateOfUse;
        this.recommendedResult = new LinkedHashMap<>();
        addUser(users);
    }

    public List<DayOfWeek> getDateOfUse() {
        return dateOfUse;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Menu> getUserMenu(String name) {
        return recommendedResult.get(name);
    }

    public LinkedHashMap<String, List<Menu>> getRecommendResult() {
        return recommendedResult;
    }

    public void addCategory(List<Category> categories) {
        this.categories = categories;
    }

    public void addMenu(String name, Menu menu) {
        recommendedResult.get(name).add(menu);
    }

    private void addUser(List<String> users) {
        for (String user : users) {
            recommendedResult.put(user, new ArrayList<>());
        }
    }
}
