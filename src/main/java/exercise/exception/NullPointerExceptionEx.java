package exercise.exception;

public class NullPointerExceptionEx {

    public static void main(String[] args) {
        String str = null;
        String[] strings = null;

        try {
            System.out.println(str.charAt(0));
            System.out.println(strings[0]);
        } catch (NullPointerException e){
            System.out.println(e);
            System.out.println(e.getClass());
            System.out.println(e.getStackTrace());
            System.out.println(e.toString());
        }
    }
}
