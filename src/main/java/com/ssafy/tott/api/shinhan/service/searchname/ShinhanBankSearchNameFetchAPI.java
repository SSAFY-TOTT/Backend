package com.ssafy.tott.api.shinhan.service.searchname;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequest;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.body.ShinhanBankSearchNameResponseDataBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
@Slf4j
public class ShinhanBankSearchNameFetchAPI implements FetchAPICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_NAME}")
    private String uri;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String validateKey; // 실제 `API`가 존재시 삭제 필요

    @Override
    public ShinhanBankSearchNameResponse fetchAPI(APIRequest json) {
        return dummyFetchAPI(json);
        /* 원래라면 정상 동작하는 코드이지만 현재는 비활성화 되어서 주석 처리 */
//        ShinhanBankSearchNameRequest shinhanBankSearchNameRequest = (ShinhanBankSearchNameRequest) json;
//        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
//        return webClient
//                .post()
//                .bodyValue(shinhanBankSearchNameRequest.getJson())
//                .retrieve()
//                .bodyToMono(ShinhanBankSearchNameResponse.class)
//                .block();
    }

    private ShinhanBankSearchNameResponse dummyFetchAPI(APIRequest request) {
        if (request instanceof ShinhanBankSearchNameRequest) {
            ShinhanBankSearchNameRequest shinhanBankSearchNameRequest = (ShinhanBankSearchNameRequest) request;
            ShinhanBankAPIRequest searchNameRequest = parseByJson(shinhanBankSearchNameRequest.getJson());
            validateApiKey(searchNameRequest.getDataHeader().getApikey());
            return sendResponse((ShinhanBankSearchNameRequestDataBody) searchNameRequest.getShinhanBankDataBody());
        }
        throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
    }

    private ShinhanBankAPIRequest parseByJson(String json) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            JSONObject dataHeader = (JSONObject) object.get("dataHeader");
            JSONObject dataBody = (JSONObject) object.get("dataBody");
            String apiKey = (String) dataHeader.get("apikey");
            String bankCode = (String) dataBody.get("입금은행코드");
            String account = (String) dataBody.get("입금계좌번호");
            return ShinhanBankAPIRequest.of(apiKey, ShinhanBankSearchNameRequestDataBody.of(bankCode, account));
        } catch (ParseException e) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
        }
    }

    private void validateApiKey(String apiKey) {
        if (!apiKey.equals(validateKey)) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_API_KEY_IS_NOT_VALID);
        }
    }

    private ShinhanBankSearchNameResponse sendResponse(ShinhanBankSearchNameRequestDataBody dataBody) {
        if (validateDataBody(dataBody)) {
            return ShinhanBankSearchNameResponse.of(ResponseDataHeader.from("1"),
                    ShinhanBankSearchNameResponseDataBody.of(dataBody.getBankCode(), dataBody.getAccount(), null));
        }
        return ShinhanBankSearchNameResponse.of(ResponseDataHeader.from("0"),
                ShinhanBankSearchNameResponseDataBody.of(dataBody.getBankCode(), dataBody.getAccount(), "김신한"));
    }

    private boolean validateDataBody(ShinhanBankSearchNameRequestDataBody dataBody) {
        return !dataBody.getBankCode().equals(BankCode.SHINHAN.getCode()) ||
                !Pattern.matches("\\d{10,14}", dataBody.getAccount());
    }
}
