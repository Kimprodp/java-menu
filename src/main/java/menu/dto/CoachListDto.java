package menu.dto;

import java.util.List;
import menu.domain.user.Coach;

public class CoachListDto {

    private List<Coach> coaches;

    public CoachListDto(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
}
