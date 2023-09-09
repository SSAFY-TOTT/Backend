package com.ssafy.tott.region.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class RegionRepositoryImpl implements RegionRepositoryCustom {
    private final JPAQueryFactory query;

    public RegionRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
