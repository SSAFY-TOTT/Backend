package com.ssafy.tott.member.domain;

import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private PhoneNumber phoneNumber;

    private Long creditLine;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @Builder
    public Member(String name, Email email, Password password, PhoneNumber phoneNumber, Long creditLine) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLine = creditLine;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public String getEncryptedPassword() {
        return password.getValue();
    }
}