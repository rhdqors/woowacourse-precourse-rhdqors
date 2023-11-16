package christmas;

public class ErrorException extends IllegalArgumentException{

    public ErrorException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

}
