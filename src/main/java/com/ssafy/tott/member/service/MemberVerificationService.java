package com.ssafy.tott.member.service;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.MemberVerificationCacheRepository;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
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
                .account(accountNumber.getValue())
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

    public MemberVerificationCache verification(MemberVerificationRequest request) {
        AccountNumber accountNumber = AccountNumber.from(request.getAccountNumber());
        MemberVerificationCache memberVerificationCache = repository.findById(accountNumber.getValue())
                .orElseThrow(() -> new MemberException(MemberErrorCode.ERROR_CLIENT_BY_INVALID_FIND_BY_ACCOUNT_NUMBER));
        validateMemo(request.getMemo(), memberVerificationCache.getMemo());
        return memberVerificationCache;
    }

    private void validateMemo(String memo, String validationMemo) {
        if (!validationMemo.substring(0, 4).equals(memo)) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_NOT_EQUALS_BY_VALIDATION_MEMO);
        }
    }
}
