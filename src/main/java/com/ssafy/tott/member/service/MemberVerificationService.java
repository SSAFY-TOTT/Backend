package com.ssafy.tott.member.service;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.MemberVerificationRepository;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.util.MemoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberVerificationService {
    private final MemberVerificationRepository memberVerificationRepository;
    private final MemoFactory memoFactory;

    @Transactional
    public MemberVerificationCache cachingBySignupRequest(MemberSignupRequest request) {
        Email email = Email.from(request.getEmail());
        Password password = Password.from(request.getPassword());
        PhoneNumber phoneNumber = PhoneNumber.from(request.getPhoneNumber());
        AccountNumber accountNumber = AccountNumber.from(request.getAccountNumber());

        MemberVerificationCache cache = MemberVerificationCache.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .account(accountNumber)
                .bankCode(request.getBankCode())
                .memo(memoFactory.generateMemo())
                .build();
        return memberVerificationRepository.save(cache);
    }
}
