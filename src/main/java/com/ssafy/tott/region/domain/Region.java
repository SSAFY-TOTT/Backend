package com.ssafy.tott.region.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name= "region_identifier",
                        columnNames={"districtCode","legalDongCode"}
                )
        }
)
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    /* district : 자치구 */
    @Column(nullable = false)
    private int districtCode;

    @Column(length = 50, nullable = false)
    private String districtName;

    /* legalDong : 법정동 */
    @Column(nullable = false)
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
}
