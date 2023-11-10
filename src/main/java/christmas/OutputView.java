package christmas;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    private final InputView inputView;
    private final Menu menu;
    HashMap<String, Integer> saveOrderMenu;

    public OutputView(InputView inputView, Menu menu) {
        this.inputView = inputView;
        this.menu = menu;
    }

    public void excuteOrder() {
        inputView.inputDate();
        printMenu();
        printAllPrice();
    }

    private void printMenu() {
        saveOrderMenu = menu.saveOrderMenu();
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : saveOrderMenu.entrySet()) {
            System.out.println(entry.getKey() + " " +  entry.getValue() + "개");
        }
    }

    private void printAllPrice() {
        int price = 0;
        System.out.println("<할인 전 총주문 금액>");

        for (Map.Entry<String, Integer> entry  : saveOrderMenu.entrySet()) {
            MenuPrice menuPrice = MenuPrice.getByName(entry.getKey());
            price += menuPrice.getPrice() * entry.getValue();
        }
        System.out.println(price);
    }

}
