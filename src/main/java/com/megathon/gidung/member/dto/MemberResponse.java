package com.megathon.gidung.member.dto;

import com.megathon.gidung.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {

    private Long id;

    private String name;

    private String username;

    private String role;

    public static MemberResponse from(Member entity) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(entity.getId());
        memberResponse.setName(entity.getName());
        memberResponse.setUsername(entity.getUsername());
        memberResponse.setRole(entity.getRole());

        return memberResponse;
    }
}
