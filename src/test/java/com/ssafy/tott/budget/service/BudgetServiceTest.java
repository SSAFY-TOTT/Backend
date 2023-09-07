package com.ssafy.tott.budget.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.data.response.BudgetsResponse;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.global.fixture.BudgetFixture;
import com.ssafy.tott.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BudgetServiceTest extends ServiceTest {
    @Autowired
    private BudgetService budgetService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = saveMember();
    }

    @DisplayName("예산 목록을 정상적으로 저장한다.")
    @Test
    void saveAllTest() {
        /* Given */
        AuthMember authMember = new AuthMember(member.getId());
        BudgetsUpdateRequest request = toRequest(member);

        /* When */
        BudgetsResponse response = budgetService.saveAll(authMember, request);

        /* Then */
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response).getBudgets()).hasSize(2)
        );
    }

    @DisplayName("예산 목록을 정상적으로 조회한다.")
    @Test
    void findAllTest() {
        /* Given */
        AuthMember authMember = new AuthMember(member.getId());
        BudgetsUpdateRequest request = toRequest(member);
        budgetService.saveAll(authMember, request);

        /* When */
        BudgetsResponse response = budgetService.findAll(authMember);

        /* Then */
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response).getBudgets()).hasSize(2)
        );
    }

    private BudgetsUpdateRequest toRequest(Member member) {
        List<BudgetVO> vos = new ArrayList<>(List.of(
                BudgetVO.from(BudgetFixture.일천만원.toBudget(member)),
                BudgetVO.from(BudgetFixture.일백만원.toBudget(member)))
        );
        return new BudgetsUpdateRequest(vos);
    }
}