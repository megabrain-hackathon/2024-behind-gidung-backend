package com.megathon.gidung.member.controller;

import com.megathon.gidung.member.dto.MemberCreateRequest;
import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(
            @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        MemberResponse response = memberService.createMember(memberCreateRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMember(
            @PathVariable Long id
    ) {
        MemberResponse response = memberService.getMember(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
