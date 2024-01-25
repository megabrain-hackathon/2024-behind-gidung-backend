package com.megathon.gidung.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ChallengeRequest {

    @Getter
    @Setter
    @Schema(name = "ChallengeCreateRequest", description = "챌린지 생성 요청")
    public static class Create {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

        @NotNull(message = "시작일을 입력해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate startAt;

        @NotNull(message = "종료일을 입력해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate endAt;

        @NotNull(message = "공개 여부를 입력해주세요.")
        private Boolean isVisible;

        @NotNull(message = "회원 아이디를 입력해주세요.")
        private Long memberId;
    }

    @Getter
    @Setter
    @Schema(name = "ChallengeUpdateRequest", description = "챌린지 수정 요청")
    public static class Update {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

        @NotNull(message = "시작일을 입력해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate startAt;

        @NotNull(message = "종료일을 입력해주세요.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate endAt;

        @NotNull(message = "공개 여부를 입력해주세요.")
        private Boolean isVisible;
    }
}
