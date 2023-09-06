package com.ssafy.tott.budget.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.budget.domain.BudgetRepository;
import com.ssafy.tott.budget.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final MemberService memberService;

    public void saveAll(AuthMember authMember, BudgetsUpdateRequest request) {
        Member member = memberService.findById(authMember.getMemberId());

        member.removeBudgets();
        budgetRepository.deleteAllByMember(member);

        List<Budget> budgets = toBudgets(member, request);
        budgetRepository.saveAll(budgets);
    }

    private List<Budget> toBudgets(Member member, BudgetsUpdateRequest request) {
        return request.getBudgetSaveRequestList().stream().map(budgetSaveRequest -> Budget.builder()
                .message(budgetSaveRequest.getMessage())
                .money(budgetSaveRequest.getMoney())
                .member(member)
                .build()).collect(Collectors.toList());
    }
}
