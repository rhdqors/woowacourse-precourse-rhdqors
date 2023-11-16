package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event {
    private boolean isChampaneService;
    private boolean isWeekend;
    private final List<Integer> weekends = new ArrayList<>();
    private final List<Integer> specialDays = new ArrayList<>();

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

    public int specialDiscount(int date) {
        int discount = 0;
        if(specialDays.contains(date)) {
            discount += EventPrice.SPECIAL_DISCOUNT.getPrice();
        }
        return discount;
    }

    public String badgeDiscount(int totalDiscount) {
        if (totalDiscount >= EventPrice.SANTA_BADGE.getPrice()) {
            return "산타";
        } else if (totalDiscount >= EventPrice.TREE_BADGE.getPrice()) {
            return "트리";
        } else if (totalDiscount >= EventPrice.STAR_BADGE.getPrice()) {
            return "별";
        }
        return "없음";
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

    private void checkWeekend(int input) {
        LocalDate date = LocalDate.of(2023, 12, 1); // 12월 1일 설정
        LocalDate endOfMonth = LocalDate.of(2023, 12, 31); // 12월의 마지막 날 설정
        saveWeekends(date, endOfMonth);

        if(weekends.contains(input)) {
            isWeekend = true;
        }

        for (LocalDate day = date; !day.isAfter(endOfMonth); day = day.plusDays(1)) {
            if (day.getDayOfWeek() == DayOfWeek.SUNDAY || day.getDayOfMonth() == 25) {
                specialDays.add(day.getDayOfMonth());
            }
        }
    }

    private void saveWeekends(LocalDate date, LocalDate endOfMonth) {
        while (!date.isAfter(endOfMonth)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekends.add(Integer.parseInt(date.toString().substring(date.toString().length()-2, date.toString().length()))-1);
            }
            date = date.plusDays(1);
        }
    }

    private int dailyDiscount(String category) {
        checkWeekend(InputView.date);
        int discount = 0;

        for (Map.Entry<String, Integer> entry : Order.orderMenus.entrySet()) {
            if(MenuPrice.allMenus.containsKey(entry.getKey()) && MenuPrice.allMenus.get(entry.getKey()).equals(category)) {
                discount += (EventPrice.DAY_DISCOUNT.getPrice() * entry.getValue());
            }
        }
        return discount;
    }

}
