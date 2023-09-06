package com.ssafy.tott.member.domain;

import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.wishlist.domain.Wishlist;
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Budget> budgets = new ArrayList<>();

    @Builder
    public Member(String name, Email email, Password password, PhoneNumber phoneNumber, Long creditLine) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLine = creditLine;
        role = Role.USER;
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

    public void removeBudgets() {
        for (Budget budget : budgets) {
            budget.removeRelated();
        }
        budgets.clear();
    }
}
