package com.ssafy.tott.region.data.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tott.region.domain.Region;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Builder
@Getter
public class DistrictVO {
    private String name;
    private int code;

    @QueryProjection
    public DistrictVO(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static DistrictVO from(Region region) {
        return DistrictVO.builder()
                .code(region.getDistrictCode())
                .name(region.getDistrictName())
                .build();
    }
}
