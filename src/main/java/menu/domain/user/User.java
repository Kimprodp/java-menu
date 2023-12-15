package menu.domain.user;

import java.util.LinkedHashMap;
import java.util.List;
import menu.domain.constants.DayOfWeek;
import menu.domain.service.LunchRecommender;
import menu.domain.service.Menu;
import menu.domain.service.RecommendedResults;

public class User {

    private List<DayOfWeek> dateOfUse;
    private LinkedHashMap<String, List<Menu>> userInfo;

    public User(List<Coach> coaches, List<DayOfWeek> dateOfUse) {
        registerUserInfo(coaches);
        this.dateOfUse = dateOfUse;
    }

    public RecommendedResults requestMenuRecommendation(LunchRecommender lunchRecommender) {
        return lunchRecommender.recommendLunch(dateOfUse, userInfo);
    }

    private void registerUserInfo(List<Coach> coaches) {
        userInfo = new LinkedHashMap<>();
        for (Coach coach : coaches) {
            userInfo.put(coach.getName(), coach.getMenuToExclude());
        }
    }
}
