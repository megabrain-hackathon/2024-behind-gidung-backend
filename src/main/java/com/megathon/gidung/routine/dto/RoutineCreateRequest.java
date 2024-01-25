package com.megathon.gidung.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineCreateRequest {

    private Long memberId;

    private Long challengeId;

    private String routineTitle;

}
