package com.ssafy.tott.member.domain;

import org.springframework.data.repository.CrudRepository;

public interface MemberVerificationRepository extends CrudRepository<MemberVerificationCache, String> {
}
