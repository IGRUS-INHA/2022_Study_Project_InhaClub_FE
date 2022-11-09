package Team7.InhaClub.Security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    /** jwt 의 access key */
    private String ACCESS_KEY = "${spring.jwt.secret.access";
    /** jwt 의 refresh key */
    private String REFRESH_KEY = "${spring.jwt.secret.refresh}";

    /** access token 의 만료 기한 */
    private final Long ACCESS_TOKEN_VALID_TIME = 3 * 60 * 1000L; // 1800000ms == 3 minutes
    /** refresh token 의 만료 기한 */
    private final Long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000l; // 604,800,000ms == 2 Weeks
    /** DB 에서 가져온 유저 정보 */
    private final UserDetailsService userDetailsService;

    /** JwtProvider 객체 초기화 후 키를 base64 로 인코딩*/
    @PostConstruct
    public void init() {
        ACCESS_KEY = Base64.getEncoder().encodeToString(ACCESS_KEY.getBytes());
        REFRESH_KEY = Base64.getEncoder().encodeToString(REFRESH_KEY.getBytes());
    }

    /** ACCESS TOKEN 생성 */
    public String createAccessToken(String _userId){
        Claims claims = Jwts.claims(); // Claims == 정보의 조각
        claims.put("userId", _userId); // 유저 id에 매개변수 넣음
        claims.put("roles", "USER"); // 유저의 권한을 USER 로 설정
        Date now = new Date();
        Date exp = new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME); // 토큰의 만료기한을 설정

        return Jwts.builder()
                .setClaims(claims) // 위에서 정의한 정보를 Jwt 에 넣음
                .setIssuedAt(now) // 토큰 발행 정보
                .setExpiration(exp) // 토큰의 만료기한 토큰에 넣음
                .signWith(SignatureAlgorithm.HS256, ACCESS_KEY) // HS256으로 인증서 서명
                .compact();
    }

    /** REFRESH TOKEN 생성  */
    public String createRefreshToken(Long _id) {
        Claims claims = Jwts.claims();
        claims.put("Id", _id); // 유저 고유 id에 매개변수 넣음
        Date now = new Date();
        Date exp = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME); // 토큰의 만료기한을 설정

        return Jwts.builder()
                .setClaims(claims) // 위에서 정의한 정보를 Jwt 에 넣음
                .setIssuedAt(now) // 토큰 발행 정보
                .setExpiration(exp) // 토큰의 만료기한 토큰에 넣음
                .signWith(SignatureAlgorithm.HS256, ACCESS_KEY) // HS256으로 인증서 서명
                .compact();

    }

    /** HTTP request header 에서 access token 을 가져온다. */
    public String resolveAccessToken(HttpServletRequest _request) {
        return _request.getHeader("ACCESS_TOKEN");
    }

    /** HTTP request header 에서 refresh token 을 가져온다. */
    public String resolveRefreshToken(HttpServletRequest _request) {
        return _request.getHeader("REFRESH_TOKEN");
    }

    /** access token 을 매개변수로 받아 Jwts parser 로 Claim 형 형태로 반환하는 함수 */
    public Claims getClaimsFormAccessToken(String _token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(ACCESS_KEY))
                .parseClaimsJws(_token)
                .getBody();
    }

    /** refresh token 을 매개변수로 받아 Jwts parser 로 Claim 형 형태로 반환히는 함수 */
    public Claims getClaimsFormRefreshToken(String _token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
                .parseClaimsJws(_token)
                .getBody();
    }

    /** access token 이 유효한 상태인지 확인하는 함수
     * 토큰이 유효하면 true, 아니면 false
     * */
    public boolean isValidAccessToken(String _token) {
        try {
            Claims accessToken = getClaimsFormAccessToken(_token);
            // 로그를 찍어야하는 곳
            return true;
        } catch (ExpiredJwtException e) {
            // 로그를 찍어야하는 곳
            return false;
        } catch (JwtException e) {
            // 로그를 찍어야하는 곳
            return false;
        } catch (NullPointerException e) {
            // 로그를 찍어야하는 곳
            return false;
        }
    }

    /** refresh token 이 유효한 상태인지 확인하는 함수 */
    public boolean isValidRefreshToken(String _token) {
        try {
            Claims refreshToken = getClaimsFormRefreshToken(_token);
            // 로그를 찍어야하는 곳
            return true;
        } catch (ExpiredJwtException e) {
            // 로그를 찍어야하는 곳
            return false;
        } catch (JwtException e) {
            // 로그를 찍어야하는 곳
            return false;
        } catch (NullPointerException e) {
            // 로그를 찍어야하는 곳
            return false;
        }
    }

    /** access token 에서 인증 정보 조회 */
    public Authentication getAuthentication (String _token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) getClaimsFormAccessToken(_token).get("userId"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
