package exercise.generics;

public class GenericsMethod {

    static <T> GenericsClass<T> returnGenericsObject() {
        return new GenericsClass<T>();
    }
}

class GenericsClass<T> {

    T field;
}
