package christmas;

public class Event {
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

    private boolean isService = false;

    public boolean serviceCheck(int allPrice) {
        if(allPrice >= 120000) {
            isService = true;
        }
        return isService;
    }
}
