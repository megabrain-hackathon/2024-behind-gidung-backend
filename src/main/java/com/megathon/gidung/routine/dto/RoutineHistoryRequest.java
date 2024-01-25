package com.megathon.gidung.routine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineHistoryRequest {

    @Getter
    @Setter
    @Schema(name="RoutineHistoryCreateRequest", description="루틴 히스토리 생성")
    public static class Create{
        private Long routineId;
        private boolean isGoal;
    }

    @Getter
    @Setter
    @Schema(name="RoutineHistoryUpdateRequest", description="루틴 히스토리 업데이트")
    public static class Update{
        private Long routineId;
        private boolean isGoal;
    }
}
