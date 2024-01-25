package com.megathon.gidung.member.entity;

import com.megathon.gidung.member.dto.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, length = 50)
    private String username;

    @Column(name = "password", length = 100, nullable = true)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "role", length = 50)
    private String role;

    public static Member toEntity(MemberCreateRequest request, String role) {
        return Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .role(role)
                .build();
    }

}
