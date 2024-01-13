package assignment;

public class NaverPay extends Point implements Pay { // 주석 2. 클래스 상속 및 5. 다형성 구현 (인터페이스)

    @Override
    public boolean pay(int amount) {
        System.out.println("네이버페이 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }

    @Override
    public void point() {
        System.out.println("네이버페이 포인트가 적립되었습니다.");
    }

}
