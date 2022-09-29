package Team7.InDaClub.Exception;


public class UserIDDuplicateException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserIDDuplicateException(String _message, ErrorCode _errorCode) {
        super(_message);
        this.errorCode = _errorCode;
    }
}
