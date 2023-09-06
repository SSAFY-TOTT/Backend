package com.ssafy.tott.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer> {
}
