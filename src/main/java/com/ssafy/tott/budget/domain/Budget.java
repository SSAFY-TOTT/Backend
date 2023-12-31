package com.ssafy.tott.budget.domain;

import com.ssafy.tott.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    private Long money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Budget(int id, String message, Long money, Member member) {
        this.id = id;
        this.message = message;
        this.money = money;
        addRelatedByMember(member);
    }

    private void addRelatedByMember(Member member) {
        this.member = member;
        member.getBudgets().add(this);
    }

    public void removeRelated() {
        this.member = null;
    }
}
