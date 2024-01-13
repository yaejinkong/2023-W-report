/* [주석 1 : 과제 설명]

  객체지향 프로그래밍 평가과제 (배점 25점)
  학과 : 브라질학과
  학번 : 201900402
  이름 : 공예진

  과제 주제 : 무인과일판매점 운영 및 결제관리

*/

package assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PayMain2 {
    private static final String PRODUCT_LIST_FILE = "/Users/kong-yaejin/Desktop/Temp/productList.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> productList = readProductListFromFile();
        Map<String, Integer> productPrices = new HashMap<>();
        List<String> paymentInfoList = new ArrayList<>();

        productPrices.put("딸기", 5000);
        productPrices.put("사과", 3000);
        productPrices.put("배", 2500);
        productPrices.put("토마토", 1500);

        while (true) {
            try {
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("**무인과일판매점입니다**");
                System.out.println("1. 상품 검색  |  2. 찜리스트 작성  | 3. 결제 수단 선택 | 4. 결제 내역  |  5. 종료");
                System.out.println("----------------------------------------");
                System.out.print("원하는 항목을 선택해주세요: ");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        // 주석 7. 컬렉션 프레임워크 HashMap 사용
                        System.out.println("우리 가게에는 딸기, 사과, 배, 토마토가 있습니다.");
                        System.out.print("상품을 입력하세요: ");
                        String selectedProduct = sc.next();
                        productList.add(selectedProduct);

                        if (productPrices.containsKey(selectedProduct)) {
                            int price = productPrices.get(selectedProduct);
                            System.out.println(selectedProduct + "의 가격은 " + price + "원입니다.");
                        } else {
                            System.out.println("죄송합니다. 해당 상품은 없습니다.");
                        }
                        break;

                    case 2:
                        // 주석 6. 참조타입 (배열 사용)
                        System.out.println("찜리스트를 작성해주세요.");
                        System.out.print("찜리스트에 등록할 상품의 개수를 입력하세요: ");
                        int n = sc.nextInt();
                        String[] likeArr = new String[n];

                        System.out.println("원하는 상품명을 하나씩 입력해주세요");
                        for (int i = 0; i < likeArr.length; i++) {
                            likeArr[i] = sc.next();
                        }
                        System.out.println("당신의 찜리스트는 다음과 같습니다.");
                        for (String s : likeArr) {
                            System.out.print(s + " ");
                        }
                        System.out.println();
                        break;

                    case 3:
                        PayService payService = new PayService();
                        System.out.println("kakaopay, naverpay, creditcard만 가능합니다.");
                        System.out.print("결제 수단을 입력하세요: ");
                        String payOption = sc.next();

                        if (!payOption.equals("kakaopay") && !payOption.equals("naverpay") && !payOption.equals("creditcard")) {
                            System.out.println("결제수단을 다시 입력해주세요");
                            continue;
                        }

                        System.out.print("결제 금액을 입력해주세요: ");
                        int amount = sc.nextInt();
                        sc.nextLine();

                        payService.processPay(payOption, amount);

                        paymentInfoList.add("결제 수단: " + payOption + ", 결제 금액: " + amount + "원");
                        break;

                    case 4:
                        // 주석 8. 파일 입출력 사용
                        writeToFile(productList, paymentInfoList);
                        printLists(productList, paymentInfoList);
                        break;

                    case 5:
                        System.out.println("종료");
                        return;

                    default:
                        System.out.println("올바른 옵션을 선택하세요.");
                }
            } catch (Exception e) { // 주석 4. 예외 처리
                System.out.println("입력 오류가 발생했습니다. 다시 시도해주세요.");
                sc.nextLine();
            }
        }
    }

    private static List<String> readProductListFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_LIST_FILE))) {
            List<String> productList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                productList.add(line);
            }
            return productList;
        } catch (IOException e) {
            System.out.println("상품 리스트 파일을 읽어오는데 문제가 발생했습니다. 새로운 리스트를 생성합니다.");
            return new ArrayList<>();
        }
    }

    private static void writeToFile(List<String> productList, List<String> paymentInfoList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_LIST_FILE, true))) {
            writer.newLine();
            writer.newLine();
            for (String paymentInfo : paymentInfoList) {
                writer.write(paymentInfo);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일에 쓰는데 문제가 발생했습니다.");
        }
    }

    private static void printLists(List<String> productList, List<String> paymentInfoList) {
        System.out.println("\n====== Payment Information ======");
        for (String paymentInfo : paymentInfoList) {
            System.out.println(paymentInfo);
        }
    }
}