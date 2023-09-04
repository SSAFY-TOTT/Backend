package com.ssafy.tott.member.service;

import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
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
    private final MemoFactory memoFactory;

    @Transactional
    public MemberSignupResponse save(MemberSignupRequest request) {
        /* TODO: 2023/09/04 검증 */
        Email email = Email.from(request.getEmail());
        Password password = Password.from(request.getPassword());
        PhoneNumber phoneNumber = PhoneNumber.from(request.getPhoneNumber());

        /* TODO: 2023/09/04 1원 이체 API 호출 */

        /* TODO: 2023/09/04 `Redis`에 값 저장 */

        /* TODO: 2023/09/04 사용자에게 값 반환 */
        String memo = memoFactory.generateMemo();
        return MemberSignupResponse.from(memo);
    }
}
