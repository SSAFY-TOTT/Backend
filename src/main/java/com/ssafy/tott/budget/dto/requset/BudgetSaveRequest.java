package com.ssafy.tott.budget.dto.requset;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BudgetSaveRequest {
    private Long money;
    private String message;
}
