package com.ssafy.tott.budget.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.data.dto.response.BudgetsResponse;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.budget.domain.BudgetRepository;
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

    @Transactional
    public BudgetsResponse saveAll(AuthMember authMember, BudgetsUpdateRequest request) {
        Member member = memberService.findById(authMember.getMemberId());

        member.removeBudgets();
        budgetRepository.deleteAllByMember(member);

        List<Budget> budgets = toBudgets(member, request);

        List<Budget> savedBudgets = budgetRepository.saveAll(budgets);
        return new BudgetsResponse(savedBudgets.stream()
                .map(BudgetVO::from)
                .collect(Collectors.toList()));
    }

    private List<Budget> toBudgets(Member member, BudgetsUpdateRequest request) {
        return request.getBudgetSaveRequestList().stream().map(budgetSaveRequest -> Budget.builder()
                .message(budgetSaveRequest.getMessage())
                .money(budgetSaveRequest.getMoney())
                .member(member)
                .build()).collect(Collectors.toList());
    }

    public BudgetsResponse findAll(AuthMember authMember) {
        Member member = memberService.findById(authMember.getMemberId());

        List<Budget> budgets = budgetRepository.findByMember(member);

        return new BudgetsResponse(budgets.stream()
                .map(BudgetVO::from)
                .collect(Collectors.toList()));
    }
}
