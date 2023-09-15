package com.ssafy.tott.budget.data.dto.requset;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetsUpdateRequest {
    private List<BudgetVO> budgetSaveRequestList;
    private int annualIncome;

    public BudgetsUpdateRequest(List<BudgetVO> budgetSaveRequestList, int annualIncome) {
        this.annualIncome = annualIncome;
        this.budgetSaveRequestList = budgetSaveRequestList;
    }
}
