package com.ssafy.tott.member.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.member.data.cond.MemberExistBySignupCond;
import com.ssafy.tott.member.domain.Member;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.ssafy.tott.member.domain.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    public MemberRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public boolean existMemberBySignupCond(MemberExistBySignupCond cond) {
        Optional<Member> findMember = Optional.ofNullable(query.selectFrom(member)
                .where(member.email.eq(cond.getEmail())
                        .or(member.phoneNumber.eq(cond.getPhoneNumber())))
                .fetchOne());
        return findMember.isPresent();
    }
}
