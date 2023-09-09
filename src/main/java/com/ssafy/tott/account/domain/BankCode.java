package com.ssafy.tott.account.domain;

import lombok.Getter;

@Getter
public enum BankCode {
    KOOKMIN("004", "국민"),
    WOORI("020", "우리"),
    HANA("081", "하나"),
    SHINHAN("088", "신한");

    private final String code;
    private final String name;

    BankCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
