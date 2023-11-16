package christmas;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    private final InputView inputView;
    private final Order order;
    private final Event event;

    private int allPrice;
    private int sumDiscount;
    private HashMap<String, Integer> saveOrderMenu;

    public OutputView(InputView inputView, Order order, Event event) {
        this.inputView = inputView;
        this.order = order;
        this.event = event;
    }

    public void excuteOrder() {
        inputView.inputDate();
//        inputView.inputMenu();
        printMenu();
        printAllPriceAndService();
        calculateEvent();
        printBadge();
    }

    private void printMenu() {
        saveOrderMenu = order.saveOrderMenu();
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : saveOrderMenu.entrySet()) {
            System.out.println(entry.getKey() + " " +  entry.getValue() + "개");
        }
    }

    private void printAllPriceAndService() {
        allPrice = order.savePriceBeforeDiscount();
        boolean isService = event.serviceChampaneCheck(allPrice);
        String result = "없음";

        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", allPrice));
        System.out.println("<증정 메뉴>");

        if(isService) {
            result = MenuPrice.CHAMPAGNE.getName() + " 1개";
        }
        System.out.println(result);
    }

    private void calculateEvent() {
        int dDayDiscount = event.dDayDiscount();
        int dailyDiscount = event.weekDayDiscount();
        int specialDiscount = event.specialDiscount(InputView.date);

//        sumDiscount = dDayDiscount + dailyDiscount + specialDiscount + MenuPrice.CHAMPAGNE.getPrice();
        sumDiscount = dDayDiscount + dailyDiscount + specialDiscount;
        if(allPrice >= EventPrice.CHAMPAGNE_SERVICE.getPrice()) {
            sumDiscount = dDayDiscount + dailyDiscount + specialDiscount + MenuPrice.CHAMPAGNE.getPrice();
        }
        printEventList(dDayDiscount, dailyDiscount, specialDiscount);
    }

    private void printEventList(int dDayDiscount, int dailyDiscount, int specialDiscount) {
        if (allPrice < EventPrice.START_SERVICE.getPrice()) {
            System.out.println("<혜택 내역>\n없음");
            return;
        }
        String present = "";
        int sumPrice = dDayDiscount + dailyDiscount + specialDiscount;
        String daily = String.format("평일 할인: -%,d원", event.weekDayDiscount());

        if(event.isWeekend())
            daily = String.format("주말 할인: -%,d원", event.weekendDiscount());
        if(allPrice >= EventPrice.CHAMPAGNE_SERVICE.getPrice())
            present = String.format("증정 이벤트: -%,d원", MenuPrice.CHAMPAGNE.getPrice());

        String output = String.format("크리스마스 디데이 할인: -%,d원\n", dDayDiscount)
                + daily + "\n"
                + String.format("특별 할인: -%,d원\n", specialDiscount)
                + present;
        if(allPrice < EventPrice.CHAMPAGNE_SERVICE.getPrice() && sumPrice == 0) {
            output = "없음";
        }
        printDiscount(output);
    }

    private void printDiscount(String output) {
        System.out.println("<혜택 내역>");
        System.out.println(output);

        System.out.println(String.format("<총혜택 금액>\n-%,d원", sumDiscount));
        System.out.println(String.format("<할인 후 예상 결제 금액>\n%,d원", allPrice - sumDiscount + MenuPrice.CHRISTMAS_PASTA.getPrice()));
    }

    private void printBadge() {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(event.badgeDiscount(sumDiscount));
    }

}
