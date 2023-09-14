package com.ssafy.tott.api.shinhan.controller;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.ShinhanBankSearchCreditLineResponse;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shinhan")
@RequiredArgsConstructor
public class ShinhanBankController {
    private final ShinhanBankAPI shinhanBankAPI;

    MemberService memberService;
    @GetMapping("/searchCreditLine")
    public ResponseEntity<String> searchCreditLine(
            @RequestParam int memberId, @RequestParam int houseGtn
    ) {
        Member member = memberService.findById(memberId);

        ShinhanBankSearchCreditLineResponse response = shinhanBankAPI.fetchSearchCreditLineAPI(
                "/Yqu0KRktzwFOQn2Yv//k254smViUMSf/0Z+z9XMIOFl8cv4OS3ZQHRIHufe61jEqLJNsOANugmvpVGpRwGdjg==",
                "04513",
                houseGtn,
                member.getAnnualIncome());

        return ResponseEntity.ok(response.getCreditLine());
    }
}
