package com.ssafy.tott.member.service;

import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.responsse.MemberSignupResponse;
import com.ssafy.tott.member.util.MemoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberVerificationService memberVerificationService;
    private final MemoFactory memoFactory;

    @Transactional
    public MemberSignupResponse signup(MemberSignupRequest request) {
        MemberVerificationCache cache = memberVerificationService.cachingBySignupRequest(request);
        /* TODO: 2023/09/04 1원 이체 API 호출 */

        return MemberSignupResponse.from(cache.getMemo());
    }
}
