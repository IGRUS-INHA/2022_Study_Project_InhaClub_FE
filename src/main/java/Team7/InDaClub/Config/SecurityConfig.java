package Team7.InDaClub.Config;

import Team7.InDaClub.Security.JwtAuthenticationFilter;
import Team7.InDaClub.Security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Jwt 의 설정 정의 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() throws Exception { // Password Encoder 정의
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // AuthenticationManager 정의
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // filterChain 의 설정 정의
        http
                .httpBasic().disable() // Security 기본 로그인페이지 미제공
                .formLogin().disable()// Security 기본 폼 로그인 미제공
                .cors().disable() // Cross Origin Resource Sharing 미허용
                .csrf().disable() // Cross Site Request Forgery 미허용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Spring Security 에서 Session 을 생성하지 않고 기존 것도 사용하지 않음
                .and() // 2회 이상으로 함수가 들어갔을때 상위로 올라오는 함수
                .authorizeRequests() // 요청에 의한 보안검사
                .antMatchers("/auth/**", "/problem/all","/user/") // 특정 리소스에서의 권한 설정
                .permitAll() // antMatchers 에서 설정된 리소스의 접근을 인증절차 없이 모두 허용으로 함
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
                );
    }
}
