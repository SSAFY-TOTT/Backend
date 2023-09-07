package com.ssafy.tott.housegeo.domain;

public enum BuildingType {
    오피스텔("오피스텔"),
    연립다세대("연립다세대"),
    아파트("아파트"),
    단독다가구("단독다가구");

    private final String buildingType;

    BuildingType(String buildingType){
        this.buildingType = buildingType;
    }

    public String getBuildingType() {
        return buildingType;
    }
}
