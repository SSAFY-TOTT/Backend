package com.ssafy.tott.member.domain;

import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private Long creditLine;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @Builder
    public Member(String name, String email, String password, String phoneNumber, Long creditLine) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLine = creditLine;
    }
}
