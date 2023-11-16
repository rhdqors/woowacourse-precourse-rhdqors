package christmas;

public enum EventPrice {
    START_SERVICE(10000),
    CHAMPAGNE_SERVICE(120000),
    D_DAY_DISCOUNT(100),
    D_DAY_BASIC_DISCOUNT(1000),
    DAY_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    SANTA_BADGE(20000),
    TREE_BADGE(10000),
    STAR_BADGE(5000);

    private final int price;

    EventPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
