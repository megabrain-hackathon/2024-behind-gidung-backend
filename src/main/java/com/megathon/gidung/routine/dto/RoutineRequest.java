package com.megathon.gidung.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineRequest {

    @Getter
    @Setter
    public static class Create{
        private Long memberId;

        private Long challengeId;

        private String routineTitle;
    }

    @Getter
    @Setter
    public static class Update{
        private Long memberId;

        private Long challengeId;

        private String routineTitle;
    }
}
