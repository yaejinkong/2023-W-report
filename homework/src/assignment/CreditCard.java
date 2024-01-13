package assignment;

public class CreditCard implements Pay { // 주석 5. 다형성 (인터페이스 상속)

    @Override
    public boolean pay(int amount) {
        System.out.println("신용카드 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
