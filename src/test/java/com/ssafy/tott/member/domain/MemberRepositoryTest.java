package com.ssafy.tott.member.domain;

import com.ssafy.tott.global.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원을 저장한다.")
    @Test
    void saveTest() {
        /* Given */
        Member saveMember = MemberFixture.JEONGUK.toMember();

        /* When */
        Member savedMember = memberRepository.save(saveMember);

        /* Then */
        assertThat(savedMember).isEqualTo(saveMember);
    }
}