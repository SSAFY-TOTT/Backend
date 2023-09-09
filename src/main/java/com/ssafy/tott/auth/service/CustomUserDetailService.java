package com.ssafy.tott.auth.service;

import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return memberRepository
        .findByEmail(Email.from(email))
        .map(this::createUserDetails)
        .orElseThrow(
            () -> new MemberException(MemberErrorCode.ERROR_CLIENT_WITH_MEMBER_IS_NOT_EXISTED));
  }

  private UserDetails createUserDetails(Member member) {
    SimpleGrantedAuthority grantedAuthority =
        new SimpleGrantedAuthority(member.getRole().getValue());
    return new User(
        String.valueOf(member.getId()),
        member.getEncryptedPassword(),
        Collections.singleton(grantedAuthority));
  }
}
