package com.ssafy.tott.account.service;

import com.ssafy.tott.account.domain.AccountRepository;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest extends ServiceTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private Member member;

    @BeforeEach
    void setup() {
        member = saveMember();
    }

    @DisplayName("계좌 조회에 성공한다.")
    @Test
    void searchAccountsSuccess() {
        /* Given */
        /* When */
        accountService.searchAccounts(member);
        /* Then */
        assertThat(accountRepository.findByMember(member)).isNotNull();
    }
}