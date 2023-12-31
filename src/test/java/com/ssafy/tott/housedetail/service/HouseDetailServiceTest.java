package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.budget.domain.BudgetRepository;
import com.ssafy.tott.budget.fixture.BudgetFixture;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailStateResponse;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.exception.HouseDetailErrorCode;
import com.ssafy.tott.housedetail.exception.HouseDetailException;
import com.ssafy.tott.housedetail.fixture.HouseDetailFixture;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.fixture.HouseGeoFixture;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HouseDetailServiceTest extends ServiceTest {

    @Autowired
    private HouseDetailService houseDetailService;
    @Autowired
    private ShinhanBankAPI shinhanBankAPI;
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private BudgetRepository budgetRepository;

    private Member member;
    private HouseGeo geo;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        geo = houseGeoRepository.save(HouseGeoFixture.GRAND_TOWER.toHouseGeo(region));
        houseDetail = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(geo));
    }

    @DisplayName("최근 본 집 목록을 조회한다.")
    @Test
    void findByRecentViewTestSuccess() {
        /* Given */
        AuthMember authMember = new AuthMember(member.getId());
        int id2 = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(geo)).getId();
        int id3 = houseDetailRepository.save(HouseDetailFixture.JU_GONG4_1.toHouseDetail(geo)).getId();

        /* When */
        HouseDetailRecentViewResponse response = houseDetailService.findByRecentView(
                authMember, new HouseDetailRecentViewRequest(List.of(id2, id3)));

        /* Then */
        assertThat(response.getHouseDetailList()).hasSize(2);
    }

    @DisplayName("사용자가 입력한 추가 자산의 총 액과 매물액에 따른 전세 대출 한도액을 조회한다.")
    @Test
    void searchStateTestSuccess() {
        /* Given */
        AuthMember authMember = new AuthMember(member.getId());

        long budgetSum = 0L;
        budgetSum += budgetRepository.save(BudgetFixture.ONE_MILLION_WON.toBudget(member)).getMoney();
        budgetSum += budgetRepository.save(BudgetFixture.TEN_MILLION_WON.toBudget(member)).getMoney();
        budgetSum += budgetRepository.save(BudgetFixture.ONE_HUNDRED_MILLION_WON.toBudget(member)).getMoney();

        long creditLine = shinhanBankAPI.fetchSearchCreditLineAPI(
                "/Yqu0KRktzwFOQn2Yv//k254smViUMSf/0Z+z9XMIOFl8cv4OS3ZQHRIHufe61jEqLJNsOANugmvpVGpRwGdjg==",
                "04513",
                houseDetail.getPrice(),
                member.getAnnualIncome()
        ).getCreditLine() * 10000L;

        houseDetailRepository.save(HouseDetailFixture.JU_GONG4_1.toHouseDetail(geo));

        HouseDetailStateResponse result = HouseDetailStateResponse.of(0L, budgetSum, creditLine);

        /* When */
        HouseDetailStateResponse response = houseDetailService.searchState(authMember, houseDetail.getPrice());

        /* Then */
        assertThat(response.getAmount()).isZero();
        assertThat(response.getBudget()).isEqualTo(result.getBudget());
    }

    @DisplayName("HouseDetail을 Id로 조회한다.")
    @Nested
    class FindByIdTest {
        @DisplayName("조회에 성공한다.")
        @Test
        void success() {
            /* Given */
            int id = houseDetail.getId();

            /* When */
            HouseDetail findDetail = houseDetailService.findById(id);

            /* Then */
            assertThat(findDetail).isEqualTo(houseDetail);
        }

        @DisplayName("조회에 실패한다.")
        @Test
        void fail() {
            /* Given */
            int id = Integer.MAX_VALUE;

            /* When */
            /* Then */
            assertThatThrownBy(() -> houseDetailService.findById(id))
                    .isInstanceOf(HouseDetailException.class)
                    .hasMessageContaining(
                            HouseDetailErrorCode.ERROR_CLIENT_WITH_HOUSE_DETAIL_IS_NOT_EXISTED.getMessage());
        }
    }
}