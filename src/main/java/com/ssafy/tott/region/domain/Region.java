package com.ssafy.tott.region.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Region(String districtCode, String districtName, String legalDongCode, String legalDongName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.legalDongCode = legalDongCode;
        this.legalDongName = legalDongName;
    }
}
