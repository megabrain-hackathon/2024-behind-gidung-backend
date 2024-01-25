package com.megathon.gidung.challenge.controller;


import com.megathon.gidung.challenge.dto.ChallengeCreateRequest;
import com.megathon.gidung.challenge.dto.ChallengeResponse;
import com.megathon.gidung.challenge.service.ChallengeService;
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
    public ResponseEntity createChallenge(
            @RequestBody ChallengeCreateRequest challengeCreateRequest
    ) {
        challengeService.createChallenge(challengeCreateRequest);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getChallenges() {
        List<ChallengeResponse> response = challengeService.getChallenges();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getChallenge(
            @PathVariable Long id
    ) {
        ChallengeResponse response = challengeService.getChallenge(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateChallenge(
            @PathVariable Long id,
            @RequestBody ChallengeCreateRequest challengeCreateRequest
    ) {
        challengeService.updateChallenge(id, challengeCreateRequest);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteChallenge(
            @PathVariable Long id
    ) {
        challengeService.deleteChallenge(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
