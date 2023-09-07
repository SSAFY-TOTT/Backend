package com.ssafy.tott.housegeo.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.region.domain.Region;
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
        name = "geo",
        uniqueConstraints = {
                @UniqueConstraint(
                        name= "house_geo_identifier",
                        columnNames={"main_number","sub_number"}
                )
        })
public class HouseGeo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_geo_id")
    private int id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(name = "main_number", nullable = false)
    private int mainNumber; // 본번

    @Column(name = "sub_number", nullable = false)
    private int subNumber;  // 부번

    @Column(nullable = false)
    private int constructionYear;

    @Column(length = 30, nullable = false)
    private String buildingName;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BuildingType buildingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "houseGeo", fetch = FetchType.LAZY)
    private List<HouseDetail> houseDetailList = new ArrayList<>();

    @Builder
    public HouseGeo(double longitude, double latitude, int mainNumber, int subNumber,
                    Region region,String buildingName,int constructionYear,BuildingType buildingType) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.mainNumber = mainNumber;
        this.subNumber = subNumber;
        this.buildingName = buildingName;
        this.buildingType = buildingType;
        this.constructionYear = constructionYear;
        addRelatedByRegion(region);
    }

    public void addRelatedByRegion(Region region) {
        this.region = region;
        region.getHouseGeoList().add(this);
    }
}
