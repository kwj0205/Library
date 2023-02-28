package com.lec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        System.out.println("passwordEncoder bean 생성");
        return new BCryptPasswordEncoder();
    }



    // ↓ SecurityFilterChain 을 Bean 으로 등록해서 사용
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())       // CSRF 비활성화
                /**********************************************
                 * ① request URL 에 대한 접근 권한 세팅  : authorizeHttpRequests()
                 * .authorizeHttpRequests( AuthorizationManagerRequestMatcherRegistry)
                 **********************************************/
                .authorizeHttpRequests(auth -> auth
                        // URL 과 접근권한 세팅(들)
                        // ↓ "/board/detail/**", "/board/write/**", "/board/update/**", "board/delete/**" URL 로 들어오는 요청은 '인증'만 필요.
                        .requestMatchers("/board/detail/**", "/board/qna/**", "/board/update/**", "board/delete/**").authenticated()
                        // ↓ 그 밖의 다른 요청은 전부 permit
                        .anyRequest().permitAll()
                )

                /**********************************************
                 * ② 폼 로그인 설정
                 * .formLogin(HttpSecurityFormLoginConfigurer)
                 * form 기반 인증 페이지 활성화.
                 **********************************************/
                .formLogin(form -> form
                        .loginPage("/user/login")   // 로그인이 필요한 상황 발생시 매개변수 url(로그인 폼)으로 request 발생
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/")     // '직접 /login' → /loginOk 에서 성공하면 "/" 로 이동시키기

                        // 로그인 성공직후 수행할 코드
                        .successHandler(new CustomLoginSuccessHandler("/menu"))

                        // 로그인 실패하면 수행할 코드
                        .failureHandler(new CustomLoginFailureHandler())
                )

                /**********************************************
                 * ③ 로그아웃 설정
                 * .Logout(LogoutConfigurer)
                 **********************************************/
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/user/logout")        // 로그아웃 수행 url
                        .invalidateHttpSession(false)     // session invalidate (디폴트:true)
                        // 이따가 CustomLogoutSuccessHandler 에서 꺼낼 정보가 있기 때문에
                        // false 로 세팅한다

                        //로그아웃 성공후 수행할 코드
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )

                .build();
    }




} // end SecurityConfig


























