package exercise.etc;

import java.util.Scanner;

public class SpringBuilderDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        // 더하기 연산은 성능 bad
        System.out.println("kim" + "nana");

        // 끝이 정해져있지 않을 때 유리
        String str1 = scanner.next();
        String str2 = scanner.next();

        stringBuilder.append("kim");
        stringBuilder.append("nana");

        System.out.println(stringBuilder.toString());
    }
}
