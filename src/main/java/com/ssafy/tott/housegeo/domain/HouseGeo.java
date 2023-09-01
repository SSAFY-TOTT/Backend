package com.ssafy.tott.housegeo.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.region.domain.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "geo")
public class HouseGeo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_geo_id")
    private int id;

    @Column(length = 20, unique = true)
    private String geoIdentifier;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(name = "main_number", nullable = false)
    private int mainNumber; // 본번

    @Column(name = "sub_number", nullable = false)
    private int subNumber;  // 부번

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
