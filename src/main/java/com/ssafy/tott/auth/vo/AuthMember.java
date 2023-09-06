package com.ssafy.tott.auth.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthMember {
    private int memberId;

    public AuthMember(int memberId) {
        this.memberId = memberId;
    }
}
