package christmas;

public enum ErrorCode {
    // 방문일
    VISIT_DATE_MISMATCH("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    // 주문시
    HAVE_NOT_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    PLESE_ENTER_THE_NUMBER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_TYPE_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    ErrorCode(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    private final String message;
}
