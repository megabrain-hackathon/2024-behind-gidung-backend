package com.megathon.gidung.challenge.dto;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeMemberRequest {

    @Getter
    @Setter
    @Schema(name = "ChallengeMemberCreateRequest", description = "챌린지 도전 요청")
    public static class Create {

        private Long challengeId;

            private Long memberId;

    }
}
