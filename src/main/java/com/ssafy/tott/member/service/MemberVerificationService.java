package com.ssafy.tott.member.service;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.MemberVerificationCacheRepository;
import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.data.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
import com.ssafy.tott.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberVerificationService {
    private final MemberVerificationCacheRepository repository;
    private final MemberMapper mapper;

    public MemberVerificationCache cachingBySignupRequest(MemberSignupRequest request) {
        MemberVerificationCache cache = mapper.toMemberVerificationCache(request, generateMemo());
        return repository.save(cache);
    }

    private String generateMemo() {
        return String.format("%04d %s", ThreadLocalRandom.current().nextInt(10000), "전세역전");
    }

    public MemberVerificationCache verification(MemberVerificationRequest request) {
        AccountNumber accountNumber = AccountNumber.from(request.getAccountNumber());
        MemberVerificationCache memberVerificationCache =
                repository
                        .findById(accountNumber.getValue())
                        .orElseThrow(
                                () ->
                                        new MemberException(
                                                MemberErrorCode.ERROR_CLIENT_BY_INVALID_FIND_BY_ACCOUNT_NUMBER));
        validateMemo(request.getMemo(), memberVerificationCache.getMemo());
        return memberVerificationCache;
    }

    private void validateMemo(String memo, String validationMemo) {
        if (!validationMemo.substring(0, 4).equals(memo)) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_NOT_EQUALS_BY_VALIDATION_MEMO);
        }
    }
}
