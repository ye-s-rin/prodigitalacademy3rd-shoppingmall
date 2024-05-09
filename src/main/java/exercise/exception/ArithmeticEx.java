package exercise.exception;

public class ArithmeticEx {

    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    public static int divide(int x, int y) throws ArithmeticException {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
        return 0;
    }
}
