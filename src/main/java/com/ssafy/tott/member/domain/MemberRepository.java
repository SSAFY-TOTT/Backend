package com.ssafy.tott.member.domain;

import com.ssafy.tott.member.domain.embbeded.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
  Optional<Member> findByEmail(Email email);
}
