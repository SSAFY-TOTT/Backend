package com.ssafy.tott.region.data.vo;

import com.ssafy.tott.region.domain.Region;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class LegalDongVO {
    private String name;
    private int code;

    public static LegalDongVO from(Region region) {
        return LegalDongVO.builder()
                .code(region.getLegalDongCode())
                .name(region.getLegalDongName())
                .build();
    }
}
