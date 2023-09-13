package com.ssafy.tott.budget.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.data.dto.response.BudgetsResponse;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.fixture.BudgetFixture;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.global.fixture.MemberFixture;
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
    private Member savedMember;
    private AuthMember authMember;

    @BeforeEach
    void setUp() {
        savedMember = saveMember(MemberFixture.SHINHAN);
        authMember = new AuthMember(savedMember.getId());
    }

    @DisplayName("추가 예산을 정상적으로 수정한다.")
    @Test
    void updateAllTest() {
        /* Given */
        BudgetsUpdateRequest request = toRequest(savedMember);

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
        budgetService.saveAll(authMember, toRequest(savedMember));

        /* When */
        BudgetsResponse response = budgetService.findAll(authMember);

        /* Then */
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response).getBudgets()).hasSize(2)
        );
    }

    private BudgetsUpdateRequest toRequest(Member member) {
        return new BudgetsUpdateRequest(new ArrayList<>(List.of(
                BudgetVO.from(BudgetFixture.TEN_MILLION_WON.toBudget(member)),
                BudgetVO.from(BudgetFixture.ONE_MILLION_WON.toBudget(member)))));
    }
}
