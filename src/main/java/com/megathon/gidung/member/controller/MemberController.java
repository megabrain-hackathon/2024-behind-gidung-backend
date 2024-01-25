package com.megathon.gidung.member.controller;

import com.megathon.gidung.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String helloWorld() {
        return memberService.hello();
    }

}
