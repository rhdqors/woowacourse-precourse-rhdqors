package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    public static final HashMap<String, Integer> orderMenus = new HashMap<>();
    private final InputView inputView;

    public Order(InputView inputView) {
        this.inputView = inputView;
    }

    public HashMap<String, Integer> saveOrderMenu() {
        String input = inputView.inputMenu();
        List<String> menus = List.of(input.split(","));

        for (String menu : menus) {
            String[] parts = menu.split("-");
            String key = parts[0];
            int value = Integer.parseInt(parts[1]);
            orderMenus.put(key, value);
        }
        return orderMenus;
    }

    public int savePriceBeforeDiscount() {
        int price = 0;
        for (Map.Entry<String, Integer> entry : orderMenus.entrySet()) {
            MenuPrice menuPrice = MenuPrice.getByName(entry.getKey());
            if (menuPrice != null) {
                price += menuPrice.getPrice() * entry.getValue();
            }
        }
        return price;
    }
}