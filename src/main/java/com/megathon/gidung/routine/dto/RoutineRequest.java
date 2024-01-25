package com.megathon.gidung.routine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineRequest {

    @Getter
    @Setter
    @Schema(name="RoutineCreateRequest", description="루틴 생성")
    public static class Create{
        private Long memberId;

        private Long challengeId;

        private String routineTitle;
    }

    @Getter
    @Setter
    @Schema(name="RoutineUpdateRequest", description="루틴 업데이트")
    public static class Update{
        private Long memberId;

        private Long challengeId;

        private String routineTitle;
    }
}
