package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Baseball {

    private static int[] answers = {5, 2, 7};

    public static void main(String[] args) {
        int count = 10;

        Scanner scanner = new Scanner(System.in);
        System.out.println("0~9까지 한번씩만 사용하여 012 이상 987 이하의 수를 입력하세요. (3개의 수를 입력할 것)");

        while (count > 0) {
            try {
                System.out.println("남은 횟수: " + count);

                String input = scanner.next();
                if (Integer.parseInt(input) > 987 || Integer.parseInt(input) < 12 || input.length() != 3) {
                    System.out.println("! 알맞은 수를 입력하세요");
                    continue;
                }

                int inputInt = Integer.parseInt(input);
                Set<Integer> set = new HashSet<>();
                set.add(first(inputInt));
                set.add(second(inputInt));
                set.add(third(inputInt));
                if (set.size() < 3) {
                    System.out.println("! 중복 없이 입력하세요");
                    count--;
                    continue;
                }

                String result = play(inputInt);
                System.out.println(result);

                if (result.equals("0O 3S 0B")) {
                    System.out.println("이겼습니다.");
                    scanner.close();
                    return;
                }

                count--;
            } catch (InputMismatchException e) {
                System.out.println("! 숫자로 입력하세요");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("! 숫자로 입력하세요");
                scanner.nextLine();
            }
        }

        System.out.println("졌습니다.");
        scanner.close();
    }

    public static int first(int num) {
        return num / 100;
    }

    public static int second(int num) {
        return num / 10 % 10;
    }

    public static int third(int num) {
        return num % 10;
    }

    public static String play(int num) {
        int[] inputs = {first(num), second(num), third(num)};
        int out = 0;
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            if (!Arrays.stream(answers).anyMatch(x -> x == inputs[finalI])) {
                out++;
            } else if (inputs[i] == answers[i]) {
                strike++;
            } else {
                ball++;
            }
        }

        return out + "O " + strike + "S " + ball + "B";
    }
}
