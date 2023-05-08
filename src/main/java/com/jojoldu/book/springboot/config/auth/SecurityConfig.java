package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() // URL별 권한관리 설정 옵션 시작점
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll() // 전체 열람
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한 가진 사람만 가능
                    .anyRequest().authenticated() // 설정값 이외 URL은 인증된 사용자만 가능
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()// oauth2 설정 진입점
                    .userInfoEndpoint() // oauth 로그인 성공 후 사용자 정보 가져올 때 설정
                    .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치 진행할 UserService 인터페이스의 구현체
                                                            // 리로스 서버(소셜 서비스)에서 사용자 정보 가져온 상태에서 추가로 진행할 기능 명시 가능
    }
}
