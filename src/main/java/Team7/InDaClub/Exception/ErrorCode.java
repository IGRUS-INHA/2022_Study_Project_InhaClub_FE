package Team7.InDaClub.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/** 이 프로젝트에서만 사용할 Error Code 의 정의 모음 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 404 BAD_REQUEST : 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    // 404 NOT_FOUND : 리소스 찾을 수 없음
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다."),
    // 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),
    // 500 INTERNAL_SERVER_ERROR : 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),

    // 아이디 중복
    USERID_DUPLICATION(HttpStatus.BAD_REQUEST, "아이디가 중복되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}