package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.List;

public class InputView {
    public static int date;

    public void inputDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        try {
            String inputDate = Console.readLine();
            validateInputDate(inputDate);

            date = Integer.parseInt(inputDate);
        } catch (ErrorException e){
            System.out.println(e.getMessage());
            inputDate();
        }
    }

    public String inputMenu() {
        String inputMenus = "";
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        try {
            inputMenus = Console.readLine().replace(" ", "");
            List<String> menus = List.of(inputMenus.split(","));

            validateDuplicateMenu(menus);
            validateOrderMenus(menus);
            System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        } catch (ErrorException e) {
            System.out.println(e.getMessage());
            inputMenu();
        }
        return inputMenus;
    }

    private void validateOrderMenus(List<String> menus) {
        for (String menu : menus) {
            String[] parts = menu.split("-");
            validateType(parts);
            validateMenu(parts[0], parts[1]);
        }
    }

    private void validateType(String[] parts) {
        if (parts.length != 2) {
            throw new ErrorException(ErrorCode.ORDER_TYPE_ERROR);
        }
    }

    private void validateDuplicateMenu(List<String> menus) {
        HashSet<String> set = new HashSet<>();
        for (String menu : menus) {
            if (!set.add(menu)) {
                throw new ErrorException(ErrorCode.DUPLICATE_MENU);
            }
        }
    }

    private void validateInputDate(String inputDate) {
        if(!inputDate.matches("([1-9]|[12][0-9]|3[01])")) {
            throw new ErrorException(ErrorCode.VISIT_DATE_MISMATCH);
        }
    }

    private void validateMenu(String menu, String countStr) {
        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            throw new ErrorException(ErrorCode.PLESE_ENTER_THE_NUMBER);
        }

        if (count < 1) {
            throw new ErrorException(ErrorCode.PLESE_ENTER_THE_NUMBER);
        }
        if (!MenuPrice.allMenus.containsKey(menu)) {
            throw new ErrorException(ErrorCode.NOT_MENU);
        }
    }

}