package exercise.generics;

import java.util.ArrayList;

public class GenericsDemo {

    public static void main(String[] args) {
        // 3종류의 차가 순서대로 honk()

        // 배열은 크기 고정
        Car[] cars = {new Taxi(), new Bus(), new Truck()};

        for (Car car : cars) {
            car.honk();
        }

        // 리스트는 크기 가변적
        ArrayList<Car> cars2 = new ArrayList<>();
        cars2.add(new Taxi());
        cars2.add(new Bus());
        cars2.add(new Truck());

        for (Car car : cars2) {
            car.honk();
        }

        // generic
        CarList<Car> carList = new CarList<>();
        carList.add(new Taxi());
        carList.add(new Bus());
        carList.add(new Truck());

        for (Car car : carList.getArrayList()) {
            car.honk();
        }
    }
}

class CarList<T> {

    ArrayList<T> arrayList = new ArrayList<>();

    void add(T data){
        this.arrayList.add(data);
    }

    ArrayList<T> getArrayList(){
        return this.arrayList;
    }

    int size(){
        return this.arrayList.size();
    }

    T get(int idx){
        return this.arrayList.get(idx);
    }
}

class Car {

    void honk() {
        System.out.println("경적 소리");
    }
}

class Taxi extends Car {

    @Override
    void honk() {
        System.out.println("뛰뛰");
    }
}

class Bus extends Car {

    @Override
    void honk() {
        System.out.println("빵빵");
    }
}

class Truck extends Car {

    @Override
    void honk() {
        System.out.println("빠아앙");
    }
}