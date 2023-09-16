package com.ssafy.tott.account.service;

import com.ssafy.tott.account.data.dto.response.FindMoneyResponse;
import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.account.domain.AccountRepository;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.ShinhanBankSearchAccountsResponse;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body.ShinhanBankSearchAccountsResponseAccount;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.service.BudgetService;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountService {
    private final MemberService memberService;
    private final AccountRepository accountRepository;
    private final ShinhanBankAPI shinhanBankAPI;
    private final PasswordEncoder passwordEncoder;
    private final BudgetService budgetService;

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

    public FindMoneyResponse findMoneyByCond(AuthMember authMember, Integer price) {
        Member member = memberService.findById(authMember.getMemberId());
        List<BudgetVO> budgets = budgetService.findAll(authMember).getBudgets();
        long budgetSum = getBudgetSum(budgets);

        List<Account> accounts = accountRepository.findByMember(member);
        long accountSum = accounts.stream().mapToLong(Account::getAmount).sum();

        if (price == 0) {
            return FindMoneyResponse.of(budgetSum, accountSum);
        }

        long creditLine = shinhanBankAPI.fetchSearchCreditLineAPI(
                "/Yqu0KRktzwFOQn2Yv//k254smViUMSf/0Z+z9XMIOFl8cv4OS3ZQHRIHufe61jEqLJNsOANugmvpVGpRwGdjg==",
                "04513",
                price,
                member.getAnnualIncome()
        ).getCreditLine() * 10000L;

        return FindMoneyResponse.of(budgetSum, accountSum, creditLine);
    }

    private long getBudgetSum(List<BudgetVO> budgetVOList) {
        return budgetVOList.stream().mapToLong(BudgetVO::getMoney).sum();
    }
}
