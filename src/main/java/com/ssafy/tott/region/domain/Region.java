package com.ssafy.tott.region.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    /* district : 자치구 */
    @Column(length = 20, nullable = false)
    private String districtCode;

    @Column(length = 50, nullable = false)
    private String districtName;

    /* legalDong : 법정동 */
    @Column(length = 20, unique = true, nullable = false)
    private String legalDongCode;

    @Column(length = 50, nullable = false)
    private String legalDongName;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<HouseGeo> houseGeoList = new ArrayList<>();

    public Region(String districtCode, String districtName, String legalDongCode, String legalDongName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.legalDongCode = legalDongCode;
        this.legalDongName = legalDongName;
    }
}
