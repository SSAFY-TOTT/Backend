package com.ssafy.tott.budget.data.dto.response;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetsResponse {
    List<BudgetVO> budgets;

    public BudgetsResponse(List<BudgetVO> budgets) {
        this.budgets = budgets;
    }
}
