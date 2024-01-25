package com.megathon.gidung.challenge.controller;

import com.megathon.gidung.challenge.dto.ChallengeMemberRequest;
import com.megathon.gidung.challenge.service.ChallengeMemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/challenges-members")
@RequiredArgsConstructor
public class ChallengeMemberController {

    private final ChallengeMemberService challengeMemberService;

    @PostMapping
    @Operation(summary = "챌린지 도전")
    public ResponseEntity createChallengeMember(
            @RequestBody ChallengeMemberRequest.Create challengeMemberCreateRequest
    ) {
        challengeMemberService.createChallengeMember(challengeMemberCreateRequest);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "챌린지 도전 취소")
    public ResponseEntity deleteChallengeMember(
            @RequestBody ChallengeMemberRequest.Create challengeMemberCreateRequest
    ) {
        challengeMemberService.deleteChallengeMember(challengeMemberCreateRequest);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
