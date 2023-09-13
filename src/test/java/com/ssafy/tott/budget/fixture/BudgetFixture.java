package com.ssafy.tott.budget.fixture;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum BudgetFixture {
    ONE_MILLION_WON(1_000_000L, "현금 자산"),
    TEN_MILLION_WON(10_000_000L, "주식 자산"),
    ONE_HUNDRED_MILLION_WON(100_000_000L, "비상금");

    private Long money;
    private String message;

    public Budget toBudget(Member member) {
        return Budget.builder().money(money).message(message).member(member).build();
    }

    public Budget toBudgetWithId(int sequence, Member member) {
        return Budget.builder().id(sequence).money(money).message(message).member(member).build();
    }

    public BudgetVO toBudgetVO(Member member) {
        return BudgetVO.from(toBudget(member));
    }
}
