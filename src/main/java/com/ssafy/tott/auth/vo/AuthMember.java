package com.ssafy.tott.auth.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class AuthMember {
    private int memberId;

    public AuthMember(int memberId) {
        this.memberId = memberId;
    }
}
