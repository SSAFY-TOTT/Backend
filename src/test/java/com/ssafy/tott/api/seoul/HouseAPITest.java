package com.ssafy.tott.api.seoul;

import com.ssafy.tott.api.seoul.data.RentApiModel;
import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.api.seoul.module.HouseAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HouseAPITest{

    private HouseAPI houseAPI;

    @DisplayName("api 연동 test")
    @Test
    void connectAPI() throws IOException {
        //given, when
        RentApiModel model = houseAPI.fetchAPI(1,5);

        //then
        assertEquals(5,model.getTbLnOpendataRentV().getRow().size());
    }

    @DisplayName("filtering 테스트")
    @Test
    void filteringRentHouseTest(){
        //given
        RentApiModel model = houseAPI.fetchAPI(1,5);

        //when
        List<RentRow> result = houseAPI.filteringRentHouse(model);

        //then
        assertEquals(0,result.size());
    }
}
