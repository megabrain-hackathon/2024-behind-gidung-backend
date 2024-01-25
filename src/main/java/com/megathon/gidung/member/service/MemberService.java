package com.megathon.gidung.member.service;

import com.megathon.gidung.member.dto.MemberCreateRequest;
import com.megathon.gidung.member.dto.MemberResponse;
import com.megathon.gidung.member.entity.Member;
import com.megathon.gidung.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberResponse createMember(MemberCreateRequest memberCreateRequest) {
        Member entity = Member.toEntity(memberCreateRequest, "USER");

        if (memberRepository.existsByUsername(entity.getUsername())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        memberRepository.save(entity);

        return MemberResponse.from(entity);
    }

    public MemberResponse getMember(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return MemberResponse.from(entity);
    }

    public void deleteMember(Long id) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        memberRepository.delete(entity);
    }

    public MemberResponse login(MemberCreateRequest memberCreateRequest) {
        Member entity = memberRepository.findByUsernameAndPassword(memberCreateRequest.getUsername(), memberCreateRequest.getPassword())
                .orElseThrow(() -> new RuntimeException("아이디나 비밀번호가 일치하지 않거나 존재하지 않는 회원입니다."));

        return MemberResponse.from(entity);
    }
}
