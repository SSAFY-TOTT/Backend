package com.ssafy.tott.account.service;

import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.account.domain.AccountRepository;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.ShinhanBankSearchAccountsResponse;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body.ShinhanBankSearchAccountsResponseAccount;
import com.ssafy.tott.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ShinhanBankAPI shinhanBankAPI;
    private final PasswordEncoder passwordEncoder;

    public void searchAccounts(Member member) {
        // 이름 암호화
        String encodedName = passwordEncoder.encode(member.getId() + member.getName());

        ShinhanBankSearchAccountsResponse response = shinhanBankAPI.fetchSearchAccountsAPI(encodedName);
        List<ShinhanBankSearchAccountsResponseAccount> responseAccounts =
                response.getShinhanBankSearchAccountsResponseDataBody().getAccounts();
        for (ShinhanBankSearchAccountsResponseAccount responseAccount : responseAccounts) {
            accountRepository.save(Account.builder()
                    .accountNumber(AccountNumber.from(responseAccount.getAccountNumber()))
                    .amount(Long.parseLong(responseAccount.getAmount()))
                    .member(member)
                    .bankCode(BankCode.SHINHAN)
                    .build());
        }
    }

    public long findTotalAmountByMemberId(Member member) {
        List<Account> accounts = accountRepository.findByMember(member);
        return accounts.stream().mapToLong(Account::getAmount).sum();
    }
}
