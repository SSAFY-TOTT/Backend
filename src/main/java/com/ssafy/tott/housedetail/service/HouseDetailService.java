package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.vo.HouseDetailRecentViewVO;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.mapper.HouseDetailMapper;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<HouseDetailRecentViewVO> findByRecentView(
            AuthMember authMember, HouseDetailRecentViewRequest request) {
        Member findMember = memberService.findById(authMember.getMemberId());
        List<HouseDetailRecentViewVO> list = new ArrayList<>();
        houseDetailRepository.findByRecentViewCond(HouseDetailRecentViewCond.toCond(request))
        return list;
    }
}
