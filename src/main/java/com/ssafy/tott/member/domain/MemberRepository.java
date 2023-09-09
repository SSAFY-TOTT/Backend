package com.ssafy.tott.member.domain;

import com.ssafy.tott.member.domain.embbeded.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(Email email);
}
