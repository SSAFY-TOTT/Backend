package com.ssafy.tott.housedetail.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.wishlist.domain.Wishlist;
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
@Table(name = "detail")
public class HouseDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_detail_id")
    private int id;
    private int price; // 만원 단위
    private double area;
    private int floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geo_id")
    private HouseGeo houseGeo;

    @OneToMany(mappedBy = "houseDetail", fetch = FetchType.LAZY)
    private List<Wishlist> wishlists = new ArrayList<>();

    @Builder
    public HouseDetail(int price, double area, int floor, HouseGeo houseGeo) {
        this.price = price;
        this.area = area;
        this.floor = floor;
        addRelatedByGeo(houseGeo);
    }

    private void addRelatedByGeo(HouseGeo houseGeo) {
        this.houseGeo = houseGeo;
        houseGeo.getHouseDetailList().add(this);
    }
}
