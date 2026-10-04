class Car {
    static int totalCars = 0;
    int engineNumber;

    Car(int engineNumber) {
        this.engineNumber = engineNumber;
        totalCars++;
    }
}

public class Static {
    public static void main(String[] args) {

        Car c1 = new Car(101);
        Car c2 = new Car(102);
        Car c3 = new Car(103);
        Car c4 = new Car(104);
        Car c5 = new Car(105);

        System.out.println("Total Cars: " + Car.totalCars);

        System.out.println("Car 1 Engine Number: " + c1.engineNumber);
        System.out.println("Car 2 Engine Number: " + c2.engineNumber);
        System.out.println("Car 3 Engine Number: " + c3.engineNumber);
        System.out.println("Car 4 Engine Number: " + c4.engineNumber);
        System.out.println("Car 5 Engine Number: " + c5.engineNumber);
    }
}
