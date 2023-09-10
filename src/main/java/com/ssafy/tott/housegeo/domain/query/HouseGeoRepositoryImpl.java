package com.ssafy.tott.housegeo.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class HouseGeoRepositoryImpl implements HouseGeoRepositoryCustom {
    private final JPAQueryFactory query;

    public HouseGeoRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
