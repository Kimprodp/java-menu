package menu.dto;

import java.util.LinkedHashMap;
import java.util.List;

public class RecommendedResultDto {

    private List<String> dateOfUse;
    private List<String> categories;
    private LinkedHashMap<String, List<String>> menuResult;

    public RecommendedResultDto(List<String> dateOfUse, List<String> categories,
                                LinkedHashMap<String, List<String>> menuResult) {
        this.dateOfUse = dateOfUse;
        this.categories = categories;
        this.menuResult = menuResult;
    }

    public List<String> getDateOfUse() {
        return dateOfUse;
    }

    public List<String> getCategories() {
        return categories;
    }

    public LinkedHashMap<String, List<String>> getMenuResult() {
        return menuResult;
    }
}
