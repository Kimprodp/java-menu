package menu.view;

import java.util.List;
import menu.dto.RecommendedResultDto;

public class OutputView {

    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String START_LUNCH_RECOMMENDATION = "점심 메뉴 추천을 시작합니다.";
    private static final String RESULT_LUNCH_RECOMMENDATION = "\n"+ "메뉴 추천 결과입니다.";
    private static final String RESULT_DATE_TITLE = "[ 구분 | %s ]";
    private static final String RESULT_CATEGORY_TITLE = "[ 카테고리 | %s ]";
    private static final String RESULT_MENU_TITLE = "[ %s | %s ]";
    private static final String RESULT_DIVISION = " | ";
    private static final String COMPLETE_LUNCH_RECOMMENDATION = "\n"+ "추천을 완료했습니다.";

    public static void printStartMessage() {
        System.out.println(START_LUNCH_RECOMMENDATION);
    }

    public static void printResult(RecommendedResultDto recommendedResultDto) {
        System.out.println(RESULT_LUNCH_RECOMMENDATION);
        System.out.println(String.format(RESULT_DATE_TITLE, String.join(RESULT_DIVISION,
                recommendedResultDto.getDateOfUse())));
        System.out.println(String.format(RESULT_CATEGORY_TITLE,
                String.join(RESULT_DIVISION, recommendedResultDto.getCategories())));
        for (String name : recommendedResultDto.getMenuResult().keySet()) {
            List<String> menus = recommendedResultDto.getMenuResult().get(name);
            System.out.println(String.format(RESULT_MENU_TITLE, name, String.join(RESULT_DIVISION, menus)));
        }
        System.out.println(COMPLETE_LUNCH_RECOMMENDATION);
    }

    public static void printError(String message) {
        System.out.println(ERROR_HEADER + message);
    }

}
