package christmas;

public class ErrorException extends IllegalArgumentException{
    private final ErrorCode errorCode;

    public ErrorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
