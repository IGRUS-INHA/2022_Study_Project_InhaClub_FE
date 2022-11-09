package Team7.InhaClub.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** 검증이 끝난 Jwt 로 부터 유저 정보를 받아와 UsernamePasswordAuthenticationFilter 로 전달
 * 인증 작업 진행
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    /**
     *
     * JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할을 수행한다.
     * request 로 들어오는 Jwt 유효성 검증
     */
    @Override
    public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain _chain) throws IOException, ServletException {

        String token = jwtProvider.resolveAccessToken((HttpServletRequest) _request); // 헤더에서 access token 을 받아온다.


        if (token != null && jwtProvider.isValidAccessToken(token)) { // 토큰이 유효할 경우
            Authentication authentication = jwtProvider.getAuthentication(token); // 유저 정보를 받아온다.
            SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext 에 Authentication 객체를 저장한다.
        }
        _chain.doFilter(_request, _response); // 다음 체인이 존재하는 필터로 이동
    }
}
