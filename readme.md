# 전세역전 Backend

## 사용 언어 및 프레임워크

---

![tott-backend-teckstack](https://github.com/SSAFY-TOTT/TOTT/assets/74192619/f0121d88-3a1f-4557-92e4-fd8314db2ebc)

Java : 11.x, Spring Boot : 2.7.15, MySQL : 8.0.x

## 개발 내용

### ERD

---

![erd](https://github.com/SSAFY-TOTT/TOTT/assets/74192619/bf4222cb-253b-44dc-9c5b-23f1f50ff498)

1. Region, Geo, Detail 는 부동산과 관련된 데이터를 가지고 있는 테이블입니다.
2. Member, Budget, Account는 사용자와 관련된 데이터를 가지고 있는 테이블입니다.

### API 호출 재시도 기능

---

![retry-flow](https://github.com/SSAFY-TOTT/TOTT/assets/74192619/da7c2055-f12e-4ca7-8c5d-f7506cb91dd6)

API를 호출할 때 단시간의 네트워크 오류로 인하여 예외가 발생하였을 때 다시 시도하도록 하는 기능입니다.

```java
private ShinhanBankSearchCreditLineResponse fetchAPIWithRetry(
        WebClient webClient, ShinhanBankSearchCreditLineRequest request,
        int tryCount, LocalDateTime endTime) {
    try {
        return webClient
                .post()
                .bodyValue(request.getJson())
                .retrieve()
                .bodyToMono(ShinhanBankSearchCreditLineResponse.class)
                .block();
    } catch (WebClientResponseException e) {
        if (canRetry(tryCount, endTime)) {
            return fetchAPIWithRetry(webClient, request, tryCount + 1, endTime);
        }
        throw new APIException(APIErrorCode.ERROR_SERVER_BY_OUTER_API_SERVER);
    }
}

private boolean canRetry(int tryCount, LocalDateTime endTime) {
    return tryCount <= 4 || LocalDateTime.now().isBefore(endTime);
}
```

API를 호출하면서 예외가 발생한다면 `try-catch` 를 사용하여 해당 `API`를 재시도할 수 있는지 판단합니다.

재시도할 수 있는 조건으로는 시도한 횟수가 4회 이하이거나 총 호출시간이 2초 이하일 때 발생합니다.

### API 아키텍처

---

```text
core
├── FetchAPICore.java
└── dto
    ├── APIRequest.java
    ├── APIResponse.java
    └── request
        ├── APIJsonRequest.java
        ├── APIPagingRequest.java
        └── APIQueryRequest.java

shinhan
├── ShinhanBankAPI.java
├── dto
│   ├── ShinhanBankDataBody.java
│   ├── request
│   │   ├── ShinhanBankAPIRequest.java
│   │   └── header
│   │       └── RequestDataHeader.java
│   └── response
│       ├── ShinhanBankAPIResponse.java
│       └── header
│           └── ResponseDataHeader.java
├── factory
│   └── ShinhanBankWebClientFactory.java
└── service
    ├── searchaccounts
    │   ├── ShinhanBankSearchAccountsFetchAPI.java
    │   └── dto
    │       ├── request
    │       │   ├── ShinhanBankSearchAccountsRequest.java
    │       │   └── ShinhanBankSearchAccountsRequestBody.java
    │       └── response
    │           ├── ShinhanBankSearchAccountsResponse.java
    │           └── body
    │               ├── ShinhanBankSearchAccountsResponseAccount.java
    │               └── ShinhanBankSearchAccountsResponseDataBody.java
    ├── searchcreditline
    ├── searchname
    └── transfer1
```

다음과 같이 `API`를 호출할 때 가이드라인을 제작하여 다른 사용자들의 개발을 돕습니다.

![api-core](https://github.com/SSAFY-TOTT/TOTT/assets/74192619/c53369f9-bda1-4394-b609-55b8acb32a72)

### 공공데이터 가공

---

공공데이터 중 실제로 필요한 데이터는 약 5만건 기준 10%정도 됩니다.

해당 데이터들을 얻기 위해 다음과 같은 코드를 사용하여 실제 필요한 데이터를 추가합니다.

```java
public List<RentRow> filteringRentHouse(RentAPIResponse rentAPIResponse) {
    return rentAPIResponse.getTbLnOpendataRentV().getRow().stream()
            .filter(row -> row.getRentGbn().equals("전세"))
            .filter(row -> row.getCntrctPrd().isEmpty())
            .filter(row -> row.getBobn() != null && !row.getBobn().isEmpty())
            .filter(row -> row.getBubn() != null && !row.getBubn().isEmpty())
            .filter(row -> row.getBjdongCd() != null && !row.getBjdongCd().isEmpty())
            .filter(row -> row.getBjdongNm() != null && !row.getBjdongNm().isEmpty())
            .filter(row -> row.getSggCd() != null && !row.getSggCd().isEmpty())
            .filter(row -> row.getSggNm() != null && !row.getSggNm().isEmpty())
            .filter(row -> row.getRentGtn() != null && !row.getRentGtn().equals("0"))
            .filter(row -> row.getBuildYear() != null && !row.getBuildYear().isEmpty())
            .filter(row -> row.getRentArea() != null && row.getRentArea() != 0D)
            .filter(row -> row.getFlrNo() != null && row.getFlrNo() != 0D)
            .collect(Collectors.toList());
}
```

자치구 이름 + 법정동 이름 + 본번 + 부번 를 조합한 결과를 통하여 공공데이터의 주소를 가지고 실제 좌표를 조회합니다.

```java
private KakaoAPIRequest makeRoadAddress(String sggNm, String bjDongNm, String bobn, String bubn) {
    StringBuilder sb = new StringBuilder()
            .append(sggNm).append(" ")
            .append(bjDongNm).append(" ")
            .append(bobn).append("-")
            .append(bubn);
    return KakaoAPIRequest.toRequest(sb.toString());
}

@Override
public APIResponse fetchAPI(APIRequest request) {
    KakaoAPIRequest kakaoAPIRequest = (KakaoAPIRequest) request;
    return kakaoWebClient
            .method(kakaoAddressProperties.getMethod())
            .uri(builder -> builder
                    .path(kakaoAddressProperties.getPath())
                    .queryParam("query", kakaoAPIRequest.getQuery())
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(KakaoAPIResponse.class)
            .block();
}
```

### 신한은행 API

---

1원 이체, 예금주 실명 조회 기능을 사용하여 회원가입을 진행하였습니다.

사용자가 회원가입할 때 입력한 계좌 번호로 신한 은행 API를 사용하여 1원을 이체를 합니다.

사용자는 전달 받은 인증 코드를 가지고 인증을 진행하게 되면 예금주 실명 조회를 통하여 사용자가 저장될 때 조회된 이름과 함께 저장하게 됩니다.

다음과 같은 순서도와 같이 동작합니다.

![회원가입 순서도](https://github.com/SSAFY-TOTT/TOTT/assets/74192619/21f6eed8-2446-4467-a1e2-24964547e9ef)

