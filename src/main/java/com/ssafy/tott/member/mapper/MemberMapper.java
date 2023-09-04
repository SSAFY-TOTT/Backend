package com.ssafy.tott.member.mapper;

import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toMember(ShinhanBankSearchNameResponse response, MemberVerificationCache memberVerificationCache) {
        return Member.builder()
                .name(response.getDataBody().getAccountOwnerName())
                .email(memberVerificationCache.getEmail())
                .password(memberVerificationCache.getPassword())
                .phoneNumber(memberVerificationCache.getPhoneNumber())
                .creditLine(0L)
                .build();
    }
}
