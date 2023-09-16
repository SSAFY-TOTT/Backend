package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.data.dto.response.BudgetsResponse;
import com.ssafy.tott.budget.data.vo.BudgetVO;
import com.ssafy.tott.budget.service.BudgetService;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailStateResponse;
import com.ssafy.tott.housedetail.data.vo.HouseDetailRecentViewVO;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.exception.HouseDetailErrorCode;
import com.ssafy.tott.housedetail.exception.HouseDetailException;
import com.ssafy.tott.housedetail.mapper.HouseDetailMapper;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseDetailService {
    private final HouseDetailRepository houseDetailRepository;
    private final HouseDetailMapper houseDetailMapper;
    private final ShinhanBankAPI shinhanBankAPI;
    private final BudgetService budgetService;
    private final MemberService memberService;

    /**
     * HouseDetail 데이터를 저장한다.
     *
     * @param row      row 참조
     * @param houseGeo houseGeo 참조
     */
    public void saveHouseDetail(RentRow row, HouseGeo houseGeo) {
        houseDetailRepository.save(houseDetailMapper.toEntity(row, houseGeo));
    }

    public HouseDetail findById(int id) {
        return houseDetailRepository.findById(id)
                .orElseThrow(() -> new HouseDetailException(
                        HouseDetailErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED));
    }

    public HouseDetailRecentViewResponse findByRecentView(AuthMember authMember,
                                                          HouseDetailRecentViewRequest request) {
        Member findMember = memberService.findById(authMember.getMemberId());
        List<HouseDetailRecentViewVO> list = houseDetailRepository.findByRecentViewCond(
                        HouseDetailRecentViewCond.toCond(request))
                .stream()
                .map(HouseDetailRecentViewVO::from)
                .collect(Collectors.toList());
        /* TODO: 2023/09/12 위시리스트 인지 확인 여부 필요 */
        return HouseDetailRecentViewResponse.toResponse(findMember, list);
    }

    public HouseDetailStateResponse searchState(AuthMember authMember, int houseDetailId) {
        BudgetsResponse budgetsResponse = budgetService.findAll(authMember);

        long budgetSum = getBudgetSum(budgetsResponse.getBudgets());
        int price = findById(houseDetailId).getPrice();

        Member member = memberService.findById(authMember.getMemberId());

        return HouseDetailStateResponse.of(
                budgetSum,
                shinhanBankAPI.fetchSearchCreditLineAPI(
                        "/Yqu0KRktzwFOQn2Yv//k254smViUMSf/0Z+z9XMIOFl8cv4OS3ZQHRIHufe61jEqLJNsOANugmvpVGpRwGdjg==",
                        "04513",
                        price,
                        member.getAnnualIncome()
                ).getCreditLine() * 10000L
        );
        // (단위) budgetSum: 원, creditLine: 원
    }

    private long getBudgetSum(List<BudgetVO> budgetVOList) {
        return budgetVOList.stream().mapToLong(BudgetVO::getMoney).sum();
    }

    public Optional<HouseDetail> findByDataCond(ExistByDetailCond cond) {
        return houseDetailRepository.findByDataCond(cond);
    }
}
