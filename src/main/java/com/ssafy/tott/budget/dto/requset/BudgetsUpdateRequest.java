package com.ssafy.tott.budget.dto.requset;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetsUpdateRequest {
    private List<BudgetSaveRequest> budgetSaveRequestList;
}
