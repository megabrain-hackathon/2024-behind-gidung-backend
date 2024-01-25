package com.megathon.gidung.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ChallengeCreateRequest {

    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAt;

    private Long memberId;
}
