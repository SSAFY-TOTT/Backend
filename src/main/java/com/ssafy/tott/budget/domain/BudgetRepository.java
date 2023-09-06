package com.ssafy.tott.budget.domain;

import com.ssafy.tott.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    void deleteAllByMember(Member member);
}
