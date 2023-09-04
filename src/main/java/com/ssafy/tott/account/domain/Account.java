package com.ssafy.tott.account.domain;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Embedded
    private AccountNumber number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankCode bankCode;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Account(AccountNumber number, BankCode bankCode, Long amount, Member member) {
        this.number = number;
        this.bankCode = bankCode;
        this.amount = amount;
        connectToMember(member);
    }

    /* 연관 관계 편의 메서드 */
    private void connectToMember(Member member) {
        this.member = member;
        member.getAccounts().add(this);
    }
}
