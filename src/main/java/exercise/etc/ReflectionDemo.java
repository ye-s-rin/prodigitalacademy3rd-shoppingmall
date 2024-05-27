package exercise.etc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /**
         * 최상위 클래스(타입) Object에 나의 객체를 담아요
         * 내 객체의 메소드 쓸 수 있어요?
         */

        Object newjeans = new Newjeans();
//        newjeans.sing();

        Class newJeansClass = Newjeans.class;
        Method sing = Newjeans.class.getMethod("sing");
        sing.invoke(newjeans, null);
    }
}

class Newjeans {

    public void sing() {
        System.out.println("Bubble gum");
    }
}
