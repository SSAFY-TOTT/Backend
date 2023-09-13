package com.ssafy.tott.region.domain;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "region_identifier",
                        columnNames = {"district_code", "legal_dong_code"})
        })
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    /* district : 자치구 */
    @Column(name = "district_code", nullable = false)
    private int districtCode;

    @Column(length = 50, nullable = false)
    private String districtName;

    /* legalDong : 법정동 */
    @Column(name = "legal_dong_code", nullable = false)
    private int legalDongCode;

    @Column(length = 50, nullable = false)
    private String legalDongName;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<HouseGeo> houseGeoList = new ArrayList<>();

    @Builder
    public Region(int districtCode, String districtName, int legalDongCode, String legalDongName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.legalDongCode = legalDongCode;
        this.legalDongName = legalDongName;
    }

    public static Region from(RentRow row) {
        return Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build();
    }
}
