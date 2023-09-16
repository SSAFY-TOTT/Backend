package com.ssafy.tott.budget.data.dto.response;

import com.ssafy.tott.budget.data.vo.BudgetVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class BudgetsResponse {
    private List<BudgetVO> budgets;
    private int annualIncome;

    public static BudgetsResponse of(List<BudgetVO> budgets, int annualIncome) {
        return BudgetsResponse.builder()
                .budgets(budgets)
                .annualIncome(annualIncome)
                .build();
    }
}
