package com.ssafy.tott.member.service;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.mapper.MemberMapper;
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
    private final MemberMapper mapper;

    @Transactional
    public void signup(MemberSignupRequest request) {
        MemberVerificationCache cache = memberVerificationService.cachingBySignupRequest(request);

        ShinhanBankTransfer1Response transfer1Response = (ShinhanBankTransfer1Response) shinhanBankAPI
                .fetchTransfer1API(cache.getBankCode(), cache.getAccountNumber(), cache.getMemo());
    }

    @Transactional
    public Member verification(MemberVerificationRequest request) {
        MemberVerificationCache memberVerificationCache = memberVerificationService.verification(request);

        String bankCode = memberVerificationCache.getBankCode().getCode();
        String accountNumber = memberVerificationCache.getAccountNumber();
        ShinhanBankSearchNameResponse responseAPI = (ShinhanBankSearchNameResponse) shinhanBankAPI.fetchSearchNameAPI(bankCode, accountNumber);

        Member saveMember = mapper.toMember(responseAPI, memberVerificationCache);
        return memberRepository.save(saveMember);
    }
}
