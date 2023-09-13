package com.ssafy.tott.account.domain;

import com.ssafy.tott.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByMember(Member member);
}
