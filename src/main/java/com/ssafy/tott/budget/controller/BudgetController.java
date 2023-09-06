package com.ssafy.tott.budget.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/budget")
@RestController
public class BudgetController {
    private final BudgetService budgetService;

    public ResponseEntity<Void> saveAll(@Authenticated AuthMember authMember,
                                        @RequestBody BudgetsUpdateRequest budgetsUpdateRequest) {
        budgetService.saveAll(authMember, budgetsUpdateRequest);
        return ResponseEntity.ok().build();
    }
}
