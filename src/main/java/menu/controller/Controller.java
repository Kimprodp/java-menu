package menu.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import menu.domain.constants.Category;
import menu.domain.constants.DayOfWeek;
import menu.domain.service.LunchRecommender;
import menu.domain.service.Menu;
import menu.domain.service.RecommendedResults;
import menu.domain.user.Coach;
import menu.domain.user.User;
import menu.dto.CoachNameDto;
import menu.dto.MenuNameDto;
import menu.dto.RecommendedResultDto;
import menu.view.InputView;
import menu.view.OutputView;

public class Controller {

    private LunchRecommender lunchRecommender;

    public Controller() {
        this.lunchRecommender = new LunchRecommender();
    }

    public void run() {
        OutputView.printStartMessage();
        User user = setUser();
        showResult(user.requestMenuRecommendation(lunchRecommender));
    }

    private User setUser() {
        List<Coach> coaches = execute(this::setCoaches);
        for (Coach coach : coaches) {
            execute(() -> askMenuToExclude(coach));
        }
        return new User(coaches, DayOfWeek.getWorkday());
    }

    private List<Coach> setCoaches() {
        CoachNameDto coachNameDto = execute(InputView::readCoachName);
        List<Coach> coaches = new ArrayList<>();
        for (String name : coachNameDto.getCoachNames()) {
            Coach coach = new Coach(name);
            coaches.add(coach);
        }
        return coaches;
    }

    private void askMenuToExclude(Coach coach) {
        MenuNameDto menuNameDto = execute(() -> InputView.readExcludeMenu(coach.getName()));
        List<Menu> menus = new ArrayList<>();
        for (String menuName : menuNameDto.getMenuName()) {
            menus.add(new Menu(menuName));
        }
        coach.addMenuToExclude(menus);
    }

    private void showResult(RecommendedResults recommendedResults) {
        OutputView.printResult(createResultDto(recommendedResults));
    }

    private RecommendedResultDto createResultDto(RecommendedResults recommendedResults) {
        List<String> dateOfUse = convertDateOfUse(recommendedResults.getDateOfUse());
        List<String> categories = convertCategories(recommendedResults.getCategories());
        LinkedHashMap<String, List<String>> menuResult = new LinkedHashMap<>();
        for (String name : recommendedResults.getRecommendResult().keySet()) {
            List<String> menuNames = convertMenu(recommendedResults.getRecommendResult().get(name));
            menuResult.put(name, menuNames);
        }
        return new RecommendedResultDto(dateOfUse, categories, menuResult);
    }


    /* 문자열 리스트 변환을 위한 메서드 */
    private List<String> convertDateOfUse(List<DayOfWeek> dateOfUse) {
        return dateOfUse.stream()
                .map(DayOfWeek::getName)
                .collect(Collectors.toList());
    }

    private List<String> convertCategories(List<Category> categories) {
        return categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }

    private List<String> convertMenu(List<Menu> menus) {
        return menus.stream()
                .map(Menu::getName)
                .collect(Collectors.toList());
    }


    /* 예외 발생 시, 재입력을 위한 메서드 */
    private <T> T execute(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void execute(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
