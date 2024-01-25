package com.megathon.gidung.challenge.controller;


import com.megathon.gidung.challenge.dto.ChallengeRequest;
import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.challenge.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    @PostMapping
    @Operation(summary = "챌린지 생성")
    public ResponseEntity createChallenge(
            @RequestBody ChallengeRequest.Create challengeCreateRequest
    ) {
        challengeService.createChallenge(challengeCreateRequest);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "공개된 챌린지들 조회")
    public ResponseEntity getChallenges() {
        List<ChallengeResponse> response = challengeService.getChallengesByVisible();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "챌린지 상세 조회")
    public ResponseEntity getChallenge(
            @PathVariable Long id
    ) {
        ChallengeResponse response = challengeService.getChallenge(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "챌린지 수정")
    public ResponseEntity updateChallenge(
            @PathVariable Long id,
            @RequestBody ChallengeRequest.Update challengeCreateRequest
    ) {
        challengeService.updateChallenge(id, challengeCreateRequest);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "챌린지 삭제")
    public ResponseEntity deleteChallenge(
            @PathVariable Long id
    ) {
        challengeService.deleteChallenge(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
