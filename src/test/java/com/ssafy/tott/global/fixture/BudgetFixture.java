package com.ssafy.tott.global.fixture;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum BudgetFixture {
    일백만원(1_000_000L, "추가 예산 1"),
    일천만원(10_000_000L, "추가 예산 2");

    private Long money;
    private String message;

    BudgetFixture(Long money, String message) {
        this.money = money;
        this.message = message;
    }

    public Budget toBudget(Member member) {
        return Budget.builder()
                .money(money)
                .message(message)
                .member(member)
                .build();
    }

    public BudgetVO toBudgetVO(Member member) {
        return BudgetVO.from(toBudget(member));
    }
}
