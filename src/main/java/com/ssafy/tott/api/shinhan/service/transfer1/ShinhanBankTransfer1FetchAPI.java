package com.ssafy.tott.api.shinhan.service.transfer1;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1Request;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1RequestDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseShinhanBankDataBody;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class ShinhanBankTransfer1FetchAPI implements FetchAPICore {

    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.1TRANSFER}")
    private String uri;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String validateKey; // 실제 `API`가 존재시 삭제 필요

    @Override
    public ShinhanBankTransfer1Response fetchAPI(APIRequest request) {
        return dummyFetchAPI(request);
//        ShinhanBankTransfer1Request shinhanBankTransfer1Request = (ShinhanBankTransfer1Request) request;
//        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
//        return webClient
//                .post()
//                .bodyValue(shinhanBankTransfer1Request.getJson())
//                .retrieve()
//                .bodyToMono(ShinhanBankTransfer1Response.class)
//                .block();
    }

    private ShinhanBankTransfer1Response dummyFetchAPI(APIRequest request) {
        if (request instanceof ShinhanBankTransfer1Request) {
            ShinhanBankTransfer1Request shinhanBankTransfer1Request = (ShinhanBankTransfer1Request) request;
            ShinhanBankAPIRequest searchNameRequest = parseByJson(shinhanBankTransfer1Request.getJson());
            validateApiKey(searchNameRequest.getDataHeader().getApikey());
            return sendResponse((ShinhanBankTransfer1RequestDataBody) searchNameRequest.getShinhanBankDataBody());
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
            String memo = (String) dataBody.get("입금통장메모");
            return ShinhanBankAPIRequest.of(apiKey,
                    ShinhanBankTransfer1RequestDataBody.of(getBankCode(bankCode), account, memo));
        } catch (ParseException e) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
        }
    }

    private BankCode getBankCode(String bankCode) {
        for (BankCode code : BankCode.values()) {
            if (code.getCode().equals(bankCode)) {
                return code;
            }
        }
        return null;
    }

    private void validateApiKey(String apiKey) {
        if (!apiKey.equals(validateKey)) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_API_KEY_IS_NOT_VALID);
        }
    }

    private ShinhanBankTransfer1Response sendResponse(ShinhanBankTransfer1RequestDataBody dataBody) {
        if (validateDataBody(dataBody)) {
            return ShinhanBankTransfer1Response.of(ResponseDataHeader.from("1"),
                    Transfer1ResponseShinhanBankDataBody.from(dataBody));
        }
        return ShinhanBankTransfer1Response.of(ResponseDataHeader.from("0"),
                Transfer1ResponseShinhanBankDataBody.from(dataBody));
    }

    private boolean validateDataBody(ShinhanBankTransfer1RequestDataBody dataBody) {
        return !dataBody.getBankCode().equals(BankCode.SHINHAN.getCode()) ||
                !Pattern.matches("\\d{10,14}", dataBody.getAccount());
    }
}
