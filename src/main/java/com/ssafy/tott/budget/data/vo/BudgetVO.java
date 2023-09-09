package com.ssafy.tott.budget.data.vo;

import com.ssafy.tott.budget.domain.Budget;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetVO {
  private Long money;
  private String message;

  @Builder
  private BudgetVO(Long money, String message) {
    this.money = money;
    this.message = message;
  }

  public static BudgetVO from(Budget budget) {
    return BudgetVO.builder().money(budget.getMoney()).message(budget.getMessage()).build();
  }
}
