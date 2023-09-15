package com.ssafy.tott.region.data.vo;

import com.ssafy.tott.region.domain.Region;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class DistrictVO {
    private String name;
    private int code;

    public static DistrictVO from(Region region) {
        return DistrictVO.builder()
                .code(region.getDistrictCode())
                .name(region.getDistrictName())
                .build();
    }
}
