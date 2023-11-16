package christmas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Order {
    public static final HashMap<String, Integer> orderMenus = new HashMap<>();
    private final InputView inputView;

    public Order(InputView inputView) {
        this.inputView = inputView;
    }

    public HashMap<String, Integer> saveOrderMenu() {
        String input = inputView.inputMenu().replace(" ", "");
        List<String> menus = List.of(input.split(","));
        validateDuplicateMenu(menus);

        for (String menu : menus) {
            String[] parts = menu.split("-");
            validateType(parts);

            String key = parts[0];
            String value = parts[1];

            validateMenu(key, value);
            orderMenus.put(key, Integer.parseInt(value));
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

    private void validateMenu(String menu, String countStr) {
        try {
            int count = Integer.parseInt(countStr);
            if (count < 1) {
                throw new ErrorException(ErrorCode.PLESE_ENTER_THE_NUMBER);
            }
            if (!MenuPrice.allMenus.containsKey(menu)) {
                throw new ErrorException(ErrorCode.NOT_MENU);
            }
        } catch (NumberFormatException e) {
            throw new ErrorException(ErrorCode.PLESE_ENTER_THE_NUMBER);
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
            if(set.contains(menu)) {
                throw new ErrorException(ErrorCode.DUPLICATE_MENU);
            }
            set.add(menu);
        }
    }

}
