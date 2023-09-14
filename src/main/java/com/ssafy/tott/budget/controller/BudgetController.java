package com.ssafy.tott.budget.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.data.dto.response.BudgetsResponse;
import com.ssafy.tott.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/budget")
@RestController
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping("/save")
    public ResponseEntity<BudgetsResponse> saveAll(
            @Authenticated AuthMember authMember,
            @RequestBody BudgetsUpdateRequest budgetsUpdateRequest,
            @RequestParam int annualIncome) {
        BudgetsResponse response = budgetService.saveAll(authMember, budgetsUpdateRequest, annualIncome);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<BudgetsResponse> findAll(@Authenticated AuthMember authMember) {
        BudgetsResponse response = budgetService.findAll(authMember);
        return ResponseEntity.ok(response);
    }
}
