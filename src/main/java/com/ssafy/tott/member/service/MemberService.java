package com.ssafy.tott.member.service;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.responsse.MemberSignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberVerificationService memberVerificationService;
    private final ShinhanBankAPI shinhanBankAPI;

    @Transactional
    public MemberSignupResponse signup(MemberSignupRequest request) {
        MemberVerificationCache cache = memberVerificationService.cachingBySignupRequest(request);

        ShinhanBankTransfer1Response transfer1Response = (ShinhanBankTransfer1Response) shinhanBankAPI
                .fetchTransfer1API(cache.getBankCode(), cache.getAccountNumber(), cache.getMemo());

        /* TODO: 2023/09/04 결과 보고 정상적이지 않으면 예외 처리 */
        return MemberSignupResponse.of(cache.getAccountNumber(), cache.getMemo());
    }
}
