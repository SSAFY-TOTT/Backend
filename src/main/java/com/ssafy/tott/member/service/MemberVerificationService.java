package com.ssafy.tott.member.service;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.MemberVerificationCacheRepository;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberVerificationService {
    private final MemberVerificationCacheRepository repository;

    public MemberVerificationCache cachingBySignupRequest(MemberSignupRequest request) {
        Email email = Email.from(request.getEmail());
        Password password = Password.from(request.getPassword());
        PhoneNumber phoneNumber = PhoneNumber.from(request.getPhoneNumber());
        AccountNumber accountNumber = AccountNumber.from(request.getAccountNumber());

        MemberVerificationCache cache = MemberVerificationCache.builder()
                .account(accountNumber)
                .bankCode(request.getBankCode())
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .memo(generateMemo())
                .build();
        return repository.save(cache);
    }

    private String generateMemo() {
        return String.format("%04d %s", ThreadLocalRandom.current().nextInt(10000), "전세역전");
    }
}
