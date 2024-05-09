package exercise.generics;

import java.util.Stack;

public class MyStackDemo {

    public static void main(String[] args) {
        MyStack<String> stackStr = new MyStack();

        System.out.println(stackStr.isEmpty());
        stackStr.push("a");
        stackStr.push("b");
        stackStr.push("c");

        System.out.println(stackStr.pop());
        System.out.println(stackStr.peek());

        System.out.println(stackStr.isEmpty());

        stackStr.printElements();

        MyStack<Integer> stackInt = new MyStack();

        System.out.println(stackInt.isEmpty());
        stackInt.push(1);
        stackInt.push(2);
        stackInt.push(3);

        System.out.println(stackInt.pop());
        System.out.println(stackInt.peek());

        System.out.println(stackInt.isEmpty());

        stackInt.printElements();
    }
}

class MyStack<T> {

    private Stack<T> stack = new Stack<>();

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void push(T data) {
        this.stack.push(data);
    }

    public T pop() {
        return this.stack.pop();
    }

    public T peek() {
        return this.stack.peek();
    }

    public void printElements() {
        for (T data : this.stack) {
            System.out.println(data);
        }
    }
}