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
  ONE_MILLION_WON(1_000_000L, "추가 예산 1"),
  TEN_MILLION_WON(10_000_000L, "추가 예산 2"),
  ONE_HUNDRED_MILLION_WON(100_000_000L, "추가 예산 3");

  private Long money;
  private String message;

  BudgetFixture(Long money, String message) {
    this.money = money;
    this.message = message;
  }

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
