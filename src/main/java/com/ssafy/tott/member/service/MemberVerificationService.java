package com.ssafy.tott.member.service;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.data.cond.MemberExistBySignupCond;
import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.data.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.MemberVerificationCacheRepository;
import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
import com.ssafy.tott.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberVerificationService {
    private final MemberVerificationCacheRepository memberVerificationCacheRepository;
    private final MemberRepository memberRepository;

    private final MemberMapper mapper;

    public MemberVerificationCache cachingBySignupRequest(MemberSignupRequest request) {
        MemberVerificationCache cache = mapper.toMemberVerificationCache(request, generateMemo());
        validateByExistedMember(cache);
        return memberVerificationCacheRepository.save(cache);
    }

    private String generateMemo() {
        return String.format("%04d %s", 1015, "전세역전");
//        return String.format("%04d %s", ThreadLocalRandom.current().nextInt(10000), "전세역전");  // 실제로 사용하는 값
    }

    private void validateByExistedMember(MemberVerificationCache cache) {
        if (memberRepository.existMemberBySignupCond(MemberExistBySignupCond.of(cache.getEmail(), cache.getPhoneNumber()))) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_MEMBER_IS_EXISTED);
        }
    }

    public MemberVerificationCache verification(MemberVerificationRequest request) {
        AccountNumber accountNumber = AccountNumber.from(request.getAccountNumber());
        MemberVerificationCache memberVerificationCache =
                memberVerificationCacheRepository.findById(accountNumber.getValue())
                        .orElseThrow(() ->
                                new MemberException(MemberErrorCode.ERROR_CLIENT_BY_INVALID_FIND_BY_ACCOUNT_NUMBER));
        validateMemo(request.getMemo(), memberVerificationCache.getMemo());
        return memberVerificationCache;
    }

    private void validateMemo(String memo, String validationMemo) {
        if (!validationMemo.substring(0, 4).equals(memo)) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_NOT_EQUALS_BY_VALIDATION_MEMO);
        }
    }
}
