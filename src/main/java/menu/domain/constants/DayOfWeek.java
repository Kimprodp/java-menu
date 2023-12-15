package menu.domain.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DayOfWeek {

    MONDAY("월요일", true),
    TUESDAY("화요일", true),
    WEDNESDAY("수요일", true),
    THURSDAY("목요일", true),
    FRIDAY("금요일", true),
    SATURDAY("토요일", false),
    SUNDAY("일요일", false);

    private String name;
    private boolean workday;

    DayOfWeek(String name, boolean workday) {
        this.name = name;
        this.workday = workday;
    }

    public static List<DayOfWeek> getWorkday() {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.workday)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

}
