package exercise.exception;

import java.util.Scanner;

public class AgeThrowCheckerEx {

    public static void main(String[] args) {
        /**
         * 숫자 입력
         * 당신의 나이는 00살이시네요. 반갑습니다.
         */

        Scanner scanner = new Scanner(System.in);
        int age = 0;

        while (true) {
            try{
                System.out.println("나이를 입력하세요. 범위는 0 ~ 100");
                age = scanner.nextInt();

                if (age == -1) {
                    break;
                }

                if (age < 0 || age > 100){
                    throw new InputBoundErrorEX("범위에서 벗어난 입력입니다.");
                }

                System.out.printf("당신의 나이는 %d살이시네요. 반갑습니다.\n", age);
            } catch (InputBoundErrorEX e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            } finally {
                scanner.close();
            }
        }
    }
}
