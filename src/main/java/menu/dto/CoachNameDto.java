package menu.dto;

import java.util.List;
import menu.domain.user.Coach;

public class CoachNameDto {

    private List<String> coachNames;

    public CoachNameDto(List<String> coachNames) {
        this.coachNames = coachNames;
    }

    public List<String> getCoachNames() {
        return coachNames;
    }
}
