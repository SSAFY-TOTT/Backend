package com.ssafy.tott.global.config;

import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@DataJpaTest
public abstract class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    protected Member saveMember(MemberFixture fixture) {
        return memberRepository.save(fixture.toMember());
    }
}
