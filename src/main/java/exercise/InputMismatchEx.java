package exercise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMismatchEx {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("점수를 입력하세요.");
                String input = scanner.nextLine();
                if (input.equals("q")) {
                    break;
                }
                int score = Integer.parseInt(input);

                if (score <= 100 && score >= 60) {
                    System.out.println("합격");
                } else if (score < 60 && score >= 0) {
                    System.out.println("불합격");
                } else {
                    System.out.println("error");
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        System.out.println("프로그램 종료");
        scanner.close();
    }
}
