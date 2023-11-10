package christmas;

import java.util.HashMap;
import java.util.List;

public class Menu {
    // enum에 메뉴,가격 저장, value값으로 갯수만 저장하면 될듯
    HashMap<String, Integer> orderMenus = new HashMap<>();
    private final InputView inputView;

    public Menu(InputView inputView) {
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
}
