package menu.domain.constants;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Category {

    JAPANESE(1, "일식", Arrays.asList("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼")),
    KOREAN(2, "한식", Arrays.asList("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음")),
    CHINESE(3, "중식", Arrays.asList("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채")),
    ASIAN(4, "아시안", Arrays.asList("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜")),
    WESTERN(5, "양식", Arrays.asList("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니"));

    private static final int FIRST_MENU = 0;
    private int number;
    private String CategoryName;
    private List<String> menu;

    Category(int number, String categoryName, List<String> menu) {
        this.number = number;
        CategoryName = categoryName;
        this.menu = menu;
    }

    public static Category getRandomCategory() {
        int min = Collections.min(getCategoryNumbers());
        int max = Collections.max(getCategoryNumbers());
        int randomNumber = Randoms.pickNumberInRange(min, max);

        return Arrays.stream(Category.values())
                .filter(category -> category.getNumber() == randomNumber)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    private static List<Integer> getCategoryNumbers() {
        return Arrays.stream(Category.values())
                .mapToInt(Category::getNumber)
                .boxed()
                .collect(Collectors.toList());
    }

    public int getNumber() {
        return number;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public List<String> getMenu() {
        return menu;
    }

    public String getRandomMenu() {
        return Randoms.shuffle(getMenu()).get(FIRST_MENU);
    }
}
