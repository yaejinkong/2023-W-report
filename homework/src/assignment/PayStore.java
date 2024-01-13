package assignment;
public class PayStore {

    public static Pay findPay(String option) {
        if (option.equals("kakaopay")) {
            return new KakaoPay();
        } else if (option.equals("naverpay")) {
            return new NaverPay();
        } else if (option.equals("creditcard")) {
            return new CreditCard();
        } else {
            return new DefaultPay();
        }
    }
}
