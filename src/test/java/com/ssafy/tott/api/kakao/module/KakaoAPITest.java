package com.ssafy.tott.api.kakao.module;

import com.ssafy.tott.api.kakao.data.vo.Documents;
import com.ssafy.tott.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = {"test"})
class KakaoAPITest {
    private final RentRow row =
            new RentRow(
                    "2023",
                    "11380",
                    "은평구",
                    "10300",
                    "불광동",
                    "1",
                    "대지",
                    "0105",
                    "0076",
                    3.0,
                    "20230901",
                    "전세",
                    57.76,
                    "23000",
                    "0",
                    "105-76",
                    "2018",
                    "연립다세대",
                    "",
                    "신규",
                    "",
                    "0",
                    "");

    @Autowired
    private KakaoMapFetchAPI kakaoMapFetchAPI;

    @DisplayName("kakaoAPI")
    @Test
    void kakaoAPIConnectTest() {
        Documents response =
                kakaoMapFetchAPI.kakaoAddressSearch(
                        row.getSggNm(), row.getBjdongNm(), row.getBobn(), row.getBubn());
        log.info(String.valueOf(response));

        assertThat(response).isNotNull();
    }
}
