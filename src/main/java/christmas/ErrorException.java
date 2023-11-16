package christmas;

public class ErrorException extends RuntimeException{

    public ErrorException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

}
