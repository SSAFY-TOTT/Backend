package com.ssafy.tott.housedetail.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class HouseDetailRepositoryImpl implements HouseDetailRepositoryCustom {
    private final JPAQueryFactory query;

    public HouseDetailRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


}
