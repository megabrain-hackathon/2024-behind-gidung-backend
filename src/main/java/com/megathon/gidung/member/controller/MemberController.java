package com.megathon.gidung.member.controller;

import com.megathon.gidung.member.dto.MemberCreateRequest;
import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity login(
            @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        MemberResponse response = memberService.login(memberCreateRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "회원 생성 (회원 가입)")
    public ResponseEntity createMember(
            @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        MemberResponse response = memberService.createMember(memberCreateRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "회원 단건 조회")
    public ResponseEntity getMember(
            @PathVariable Long id
    ) {
        MemberResponse response = memberService.getMember(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원 삭제")
    public ResponseEntity deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
