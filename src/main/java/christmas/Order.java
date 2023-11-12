package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order { // 고객 주문 관련 주문한 메뉴, 수량 등
    private HashMap<String, Integer> orderMenus = new HashMap<>();
    private final InputView inputView;

    public Order(InputView inputView) {
        this.inputView = inputView;
    }

    public HashMap<String, Integer> saveOrderMenu() {
        List<String> menus = List.of(inputView.inputMenu().split(","));

        for (String menu : menus) {
            String key = menu.split("-")[0];
            int value = Integer.parseInt(menu.split("-")[1]);
            orderMenus.put(key, value);
        }
        return orderMenus;
    }

    public int savePriceBeforeDiscount() {
        int price = 0;

        for (Map.Entry<String, Integer> entry  : orderMenus.entrySet()) {
            MenuPrice menuPrice = MenuPrice.getByName(entry.getKey());
            price += menuPrice.getPrice() * entry.getValue();
        }
        return price;
    }
}
