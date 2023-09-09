package com.ssafy.tott.budget.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.BDDMockito.given;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.dto.requset.BudgetsUpdateRequest;
import com.ssafy.tott.budget.data.dto.response.BudgetsResponse;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.domain.Budget;
import com.ssafy.tott.budget.domain.BudgetRepository;
import com.ssafy.tott.global.fixture.BudgetFixture;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BudgetServiceTest {

  @InjectMocks private BudgetService budgetService;
  @Mock private MemberService memberService;
  @Mock private BudgetRepository budgetRepository;
  private Member savedMember;
  private AuthMember authMember;

  @BeforeEach
  void setUp() {
    savedMember = MemberFixture.SHINHAN.toMemberWithId(1);
    authMember = new AuthMember(savedMember.getId());
    given(memberService.findById(savedMember.getId())).willReturn(savedMember);
  }

  @DisplayName("추가 예산을 정상적으로 수정한다.")
  @Test
  void updateAllTest() {
    /* Given */
    BudgetsUpdateRequest request = toRequest(savedMember);
    given(budgetRepository.saveAll(anyCollection())).willReturn(toBudgets());

    /* When */
    BudgetsResponse response = budgetService.saveAll(authMember, request);

    /* Then */
    assertAll(
        () -> assertThat(response).isNotNull(),
        () -> assertThat(Objects.requireNonNull(response).getBudgets()).hasSize(2));
  }

  @DisplayName("예산 목록을 정상적으로 조회한다.")
  @Test
  void findAllTest() {
    /* Given */
    given(budgetRepository.findByMember(savedMember)).willReturn(toBudgets());

    /* When */
    BudgetsResponse response = budgetService.findAll(authMember);

    /* Then */
    assertAll(
        () -> assertThat(response).isNotNull(),
        () -> assertThat(Objects.requireNonNull(response).getBudgets()).hasSize(2));
  }

  private BudgetsUpdateRequest toRequest(Member member) {
    return new BudgetsUpdateRequest(
        new ArrayList<>(
            List.of(
                BudgetVO.from(BudgetFixture.TEN_MILLION_WON.toBudget(member)),
                BudgetVO.from(BudgetFixture.ONE_MILLION_WON.toBudget(member)))));
  }

  private List<Budget> toBudgets() {
    int sequence = 1;
    return new ArrayList<>(
        List.of(
            toBudget(sequence++, savedMember, BudgetFixture.ONE_MILLION_WON),
            toBudget(sequence, savedMember, BudgetFixture.TEN_MILLION_WON)));
  }

  private Budget toBudget(int sequence, Member member, BudgetFixture budgetFixture) {
    return budgetFixture.toBudgetWithId(sequence, member);
  }
}
