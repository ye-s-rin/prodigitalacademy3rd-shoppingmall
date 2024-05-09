package exercise.exception;

public class ArrayIndexOutOfBoundsEx {

    public static void main(String[] args) {
        int[] ints = {0, 1, 2};
        int[] ints2 = {0, 1, 2};

        try {
            System.out.println(ints[3]);
            System.out.println(ints2[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
