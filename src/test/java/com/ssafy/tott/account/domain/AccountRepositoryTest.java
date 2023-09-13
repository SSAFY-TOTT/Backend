package com.ssafy.tott.account.domain;

import com.ssafy.tott.account.fixture.AccountFixture;
import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest extends RepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Member member;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
    }

    @DisplayName("계좌를 정상적으로 저장한다.")
    @Test
    void saveSuccess() {
        /* Given */
        Account account = AccountFixture.ACCOUNT_ONE.toAccount(member);

        /* When */
        Account savedAccount = accountRepository.save(account);

        /* Then */
        assertThat(savedAccount).isEqualTo(account);
    }
}