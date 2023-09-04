package com.ssafy.tott.member.service;

import com.ssafy.tott.member.domain.MemberVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberVerificationService {
    private final MemberVerificationRepository memberVerificationRepository;
}
