package com.webflux.security;

import java.util.regex.Pattern;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *  스프링 시큐리티는 컴포넌트 간의 리액티브 및 논블로킹 동작을 지원,
 *  리액티브 방식으로 액세스를 제공하기 위해 새로운 WebFilter 인프라를 적용하고
 *  리액터 컨택스트 기능에 크게 의존하는 새로운 리액티브를 구현하였다.
 */
@Configuration
public class SecurityConfiguration {

  private static final Pattern PASSWORD_ALGORITHM_PATTERN = Pattern.compile("^\\{.+}.*$");
  private static final String NOOP_PASSWORD_PREFIX = "{noop}";

  @Bean
  public MapReactiveUserDetailsService reactiveUserDetailsService(
      ObjectProvider<PasswordEncoder> passwordEncoder
  ) {

    return new MapReactiveUserDetailsService(
        User.withUsername("user")
            .password("user")
            .passwordEncoder(p -> getOrDeducePassword(p, passwordEncoder.getIfAvailable()))
            .roles("USER")
            .build(),
        User.withUsername("admin")
            .password("admin")
            .passwordEncoder(p -> getOrDeducePassword(p, passwordEncoder.getIfAvailable()))
            .roles("USER", "ADMIN")
            .build()
    );
  }

  @Bean
  public SecurityWebFilterChain securityFilterChainConfigurer(ServerHttpSecurity httpSecurity) {
    return httpSecurity
        .authorizeExchange()
        .anyExchange()
        .permitAll()
        .and()
        .httpBasic()
        .and()
        .formLogin()
        .and()
        .build();
  }

  private String getOrDeducePassword(
      String password,
      PasswordEncoder encoder
  ) {
    if (encoder != null || PASSWORD_ALGORITHM_PATTERN.matcher(password).matches()) {
      return password;
    }
    return NOOP_PASSWORD_PREFIX + password;
  }
}
