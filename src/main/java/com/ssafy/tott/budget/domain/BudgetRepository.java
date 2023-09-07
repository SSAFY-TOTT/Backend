package com.ssafy.tott.budget.domain;

import com.ssafy.tott.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    void deleteAllByMember(Member member);

    List<Budget> findByMember(Member member);
}
