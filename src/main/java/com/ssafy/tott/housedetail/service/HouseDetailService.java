package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.tott.housedetail.data.vo.HouseDetailRecentViewVO;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.mapper.HouseDetailMapper;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseDetailService {
    private final HouseDetailRepository houseDetailRepository;
    private final HouseDetailMapper houseDetailMapper;

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

    public HouseDetailRecentViewResponse findByRecentView(
            AuthMember authMember, HouseDetailRecentViewRequest request) {
        Member findMember = memberService.findById(authMember.getMemberId());
        List<HouseDetailRecentViewVO> list = houseDetailRepository.findByRecentViewCond(
                        HouseDetailRecentViewCond.toCond(request))
                .stream()
                .map(HouseDetailRecentViewVO::from)
                .collect(Collectors.toList());
        /* TODO: 2023/09/12 위시리스트 인지 확인 여부 필요 */
        return HouseDetailRecentViewResponse.builder()
                .memberId(findMember.getId())
                .houseDetailList(list)
                .resultCount(list.size())
                .build();
    }
}
