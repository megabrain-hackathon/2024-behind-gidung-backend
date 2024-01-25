package com.megathon.gidung.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateRequest {

    private String name;

    private String username;

    private String password;

}
