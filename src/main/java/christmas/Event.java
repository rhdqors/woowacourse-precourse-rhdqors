package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event {

    private final Order order;

    public Event(Order order) {
        this.order = order;
    }
    /*
    이벤트 목록
    1. 크리스마스 디데이 할인
       - 1일부터 25일까지 1000원 부터 100원씩 증가하여 할인
       - 25일 -> 3400원 할인
    2. 평일(일~목) 디저트 1개당 2023원 할인
    3. 주말(금~토) 메인 1개당 2023원 할인
    4. 달력에 별 있으면 총 금액-1000원
    5. 할인 전 총 주문액 12만원 이상 -> 샴페인
    6. 디데이 할인(1번) 제외 2~5번은 31일까지 적용
     */

    private boolean isChampaneService;
    private boolean isWeekend;

    public boolean serviceChampaneCheck(int allPrice) {
        if(allPrice >= EventPrice.CHAMPAGNE_SERVICE.getPrice()) {
            isChampaneService = true;
        }
        return isChampaneService;
    }

    public int dDayDiscount() {
        int firstDiscount = EventPrice.D_DAY_BASIC_DISCOUNT.getPrice();
        int discount = EventPrice.D_DAY_DISCOUNT.getPrice();

        return firstDiscount + (InputView.date-1) * discount;
    }

    public void checkWeekend(int day) {
        LocalDate date = LocalDate.of(2023, 12, 1); // 12월 1일 설정
        LocalDate endOfMonth = LocalDate.of(2023, 12, 31); // 12월의 마지막 날 설정
        List<Integer> weekends = new ArrayList<>();
        isWeekend = true;

        while (!date.isAfter(endOfMonth)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekends.add(Integer.parseInt(date.toString().substring(date.toString().length()-2, date.toString().length()))-1);
            }
            date = date.plusDays(1);
        }
        System.out.println("weekends = " + weekends);

        if(!weekends.contains(day)) {
            isWeekend = false;
        }
    }

    public int dailyDiscount(String category) {
        checkWeekend(InputView.date);
        System.out.println("isWeekend = " + isWeekend);
        int discount = 0;

        for (Map.Entry<String, Integer> entry : Order.orderMenus.entrySet()) {
            if(MenuPrice.allMenus.containsKey(entry.getKey()) && MenuPrice.allMenus.get(entry.getKey()).equals(category)) {
                discount += (2023 * entry.getValue());
            }
        }
        return discount;
    }

    public int weekendDiscount() {
        return dailyDiscount("메인");
    }

    public int weekDayDiscount() {
        return dailyDiscount("디저트");
    }

    public boolean isWeekend() {
        return this.isWeekend;
    }

}
