package com.ssafy.tott.global.config;

import com.ssafy.tott.auth.annotation.AuthenticatedArgumentResolver;
import com.ssafy.tott.auth.support.TokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final TokenProvider tokenProvider;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new AuthenticatedArgumentResolver(tokenProvider));
  }
}
