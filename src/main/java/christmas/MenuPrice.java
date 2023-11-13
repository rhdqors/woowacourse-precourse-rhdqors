package christmas;

import java.util.HashMap;

public enum MenuPrice {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAAESAR_SALAD("시저샐러드", 8000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55000),
    BBQ_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    // 음료
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;
    public static final HashMap<String, String> allMenus = new HashMap<>();

    MenuPrice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public static MenuPrice getByName(String name) {
        for (MenuPrice item : MenuPrice.values())
            if (item.getName().equals(name)) return item;
        return null;
    }

    public static void saveAllMenus() {
        allMenus.put(MUSHROOM_SOUP.name, "애피타이저");
        allMenus.put(TAPAS.name, "애피타이저");
        allMenus.put(CAAESAR_SALAD.name, "애피타이저");
        allMenus.put(T_BONE_STEAK.name, "메인");
        allMenus.put(BBQ_RIBS.name, "메인");
        allMenus.put(SEAFOOD_PASTA.name, "메인");
        allMenus.put(CHRISTMAS_PASTA.name, "메인");
        allMenus.put(CHOCOLATE_CAKE.name, "디저트");
        allMenus.put(ICE_CREAM.name, "디저트");
        allMenus.put(ZERO_COKE.name, "음료");
        allMenus.put(RED_WINE.name, "음료");
        allMenus.put(CHAMPAGNE.name, "음료");
    }

}
