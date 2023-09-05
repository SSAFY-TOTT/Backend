package com.ssafy.tott.member.service;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseShinhanBankDataBody;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.dto.responsse.MemberSignupResponse;
import com.ssafy.tott.member.dto.responsse.MemberVerificationResponse;
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
    public MemberSignupResponse signup(MemberSignupRequest request) {
        MemberVerificationCache cache = memberVerificationService.cachingBySignupRequest(request);

        ShinhanBankTransfer1Response transfer1Response = (ShinhanBankTransfer1Response)
                shinhanBankAPI.fetchTransfer1API(cache.getBankCode(), cache.getAccountNumber(), cache.getMemo());
        Transfer1ResponseShinhanBankDataBody transfer1ResponseDataBody = transfer1Response.getDataBody();

        return MemberSignupResponse.of(transfer1ResponseDataBody.getAccount(), transfer1ResponseDataBody.getBankCode());
    }

    @Transactional
    public MemberVerificationResponse verification(MemberVerificationRequest request) {
        MemberVerificationCache memberVerificationCache = memberVerificationService.verification(request);

        String bankCode = memberVerificationCache.getCodeByBankCode();
        String accountNumber = memberVerificationCache.getAccountNumber();
        ShinhanBankSearchNameResponse responseAPI = (ShinhanBankSearchNameResponse) shinhanBankAPI.fetchSearchNameAPI(bankCode, accountNumber);

        Member savedMember = memberRepository.save(mapper.toMember(responseAPI, memberVerificationCache));
        return MemberVerificationResponse.from(savedMember.getId());
    }
}
