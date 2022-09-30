package Team7.InDaClub.Exception;

/** 유저 ID가 중복될 때에 처리될 예외 */
public class UserIDDuplicateException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserIDDuplicateException(String _message, ErrorCode _errorCode) {
        super(_message);
        this.errorCode = _errorCode;
    }
}
