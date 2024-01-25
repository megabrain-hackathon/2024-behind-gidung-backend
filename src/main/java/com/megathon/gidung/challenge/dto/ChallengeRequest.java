package com.megathon.gidung.challenge.dto;

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

        @NotNull(message = "회원 아이디를 입력해주세요.")
        private Long memberId;
    }

    @Getter
    @Setter
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
    }
}
