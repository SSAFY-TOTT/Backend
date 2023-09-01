package com.ssafy.tott.housedetail.domain;

import com.ssafy.tott.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "detail")
public class HouseDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_detail_id")
    private int id;
    private long price;
    private double area;
    private int floor;
}
