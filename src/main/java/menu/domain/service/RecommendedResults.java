package menu.domain.service;

import java.util.LinkedHashMap;
import java.util.List;
import menu.domain.constants.Category;
import menu.domain.constants.DayOfWeek;

public class RecommendedResults {

    private List<DayOfWeek> dateOfUse;
    private List<Category> categories;
    private LinkedHashMap<String, List<Menu>> menuResult;

    public RecommendedResults(List<DayOfWeek> dateOfUse, List<Category> categories,
                              LinkedHashMap<String, List<Menu>> result) {
        this.dateOfUse = dateOfUse;
        this.categories = categories;
        this.menuResult = result;
    }

    public List<DayOfWeek> getDateOfUse() {
        return dateOfUse;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public LinkedHashMap<String, List<Menu>> getResult() {
        return menuResult;
    }
}
