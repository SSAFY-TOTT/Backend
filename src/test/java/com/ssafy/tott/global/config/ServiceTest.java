package com.ssafy.tott.global.config;

import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public abstract class ServiceTest {
    @Autowired
    MemberRepository memberRepository;

    public Member saveMember(MemberFixture fixture) {
        return memberRepository.save(fixture.toMember());
    }
}
