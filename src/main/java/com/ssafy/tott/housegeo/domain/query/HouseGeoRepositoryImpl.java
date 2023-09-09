package com.ssafy.tott.housegeo.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.housegeo.data.cond.HouseGeoFilterCond;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import java.util.List;
import javax.persistence.EntityManager;

public class HouseGeoRepositoryImpl implements HouseGeoRepositoryCustom {
  private final JPAQueryFactory query;

  public HouseGeoRepositoryImpl(EntityManager em) {
    this.query = new JPAQueryFactory(em);
  }

  @Override
  public List<HouseGeo> findByFilterCond(HouseGeoFilterCond cond) {
    return null;
  }
}
