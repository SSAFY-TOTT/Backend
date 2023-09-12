package com.ssafy.tott.member.domain.query;

import com.ssafy.tott.member.data.cond.MemberExistBySignupCond;

public interface MemberRepositoryCustom {
    boolean existMemberBySignupCond(MemberExistBySignupCond cond);
}
