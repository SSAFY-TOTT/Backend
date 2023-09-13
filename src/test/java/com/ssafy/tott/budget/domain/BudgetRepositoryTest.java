package com.ssafy.tott.budget.domain;

import com.ssafy.tott.budget.fixture.BudgetFixture;
import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetRepositoryTest extends RepositoryTest {
    @Autowired
    private BudgetRepository budgetRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = saveMember(MemberFixture.SHINHAN);
        budgetRepository.saveAll(new ArrayList<>(List.of(
                BudgetFixture.ONE_MILLION_WON.toBudget(member),
                BudgetFixture.TEN_MILLION_WON.toBudget(member))));
    }

    @DisplayName("추가 예산을 삭제한다.")
    @Test
    void delete() {
        /* Given */
        /* When */
        budgetRepository.deleteAllByMember(member);
        List<Budget> budgets = budgetRepository.findByMember(member);

        /* Then */
        assertThat(budgets).isEmpty();
    }

    @DisplayName("추가 예산을 추가한다.")
    @Test
    void add() {
        /* Given */
        /* When */
        budgetRepository.save(BudgetFixture.ONE_HUNDRED_MILLION_WON.toBudget(member));
        List<Budget> budgets = budgetRepository.findByMember(member);

        /* Then */
        assertThat(budgets).hasSize(3);
    }

    @DisplayName("추가 예산을 조회한다.")
    @Test
    void find() {
        /* Given */
        /* When */
        List<Budget> budgets = budgetRepository.findByMember(member);

        /* Then */
        assertThat(budgets).hasSize(2);
    }
}
