package exercise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiCatchEx {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cards = {4, 5, 6, 1, 2, 3};
        System.out.println("몇 번째 카드를 뽑으실래요?");

        try {
            int cardIdx = scanner.nextInt();
            System.out.println("뽑은 카드 적힌 숫자는: " + cards[cardIdx]);
        } catch (InputMismatchException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
