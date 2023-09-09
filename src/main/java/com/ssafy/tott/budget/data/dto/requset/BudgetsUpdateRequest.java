package com.ssafy.tott.budget.data.dto.requset;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetsUpdateRequest {
  private List<BudgetVO> budgetSaveRequestList;

  public BudgetsUpdateRequest(List<BudgetVO> budgetSaveRequestList) {
    this.budgetSaveRequestList = budgetSaveRequestList;
  }
}
