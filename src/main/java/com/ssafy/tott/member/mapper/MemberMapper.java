package com.ssafy.tott.member.mapper;

import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toMember(ShinhanBankSearchNameResponse response, MemberVerificationCache memberVerificationCache) {
        return Member.builder()
                .name(response.getOwnerName())
                .email(memberVerificationCache.getEmail())
                .password(memberVerificationCache.getPassword())
                .phoneNumber(memberVerificationCache.getPhoneNumber())
                .creditLine(0L)
                .build();
    }

    public MemberVerificationCache toMemberVerificationCache(MemberSignupRequest request, String memo) {
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
                .memo(memo)
                .build();
        return cache;
    }
}