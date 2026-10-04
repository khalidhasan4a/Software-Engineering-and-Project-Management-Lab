Date : 03/10/2026

Topic : Static & Non-Static

Code :

class Student {
    static int count = 0;

    Student() {
        count++;
    }
}

public class Static {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        System.out.println(Student.count);



    }
}

Code :

class Car {
    static int totalCars = 0;
    int engineNumber;

    Car(int engineNumber) {
        this.engineNumber = engineNumber;
        totalCars++;
    }
}

public class StaticCar {
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

        // totalCars is static, so one shared copy exists for the entire Car class.
        System.out.println("Using objects:");
        System.out.println(c1.totalCars);
        System.out.println(c2.totalCars);
    }
}

Code :

class Student {
    int count = 0;

    Student() {
        count++;
    }
}

public class NonStatic {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        System.out.println(s1.count);
        System.out.println(s2.count);
        System.out.println(s3.count);

    }
}


Date : 04/10/2026

Topic : Starting Threads: start() vs run()

Code :

class CookingTask extends Thread {
    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " - Running: " + taskName);
    }
}

public class ThreadMain {
    public static void main(String[] args) {
        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");

        task1.start(); // real, concurrent thread
        task2.start();

        System.out.println("All tasks started...");
    }
}

Code :

public class ThreadMain {
    public static void main(String[] args) {

        CookingTask task1 = new CookingTask("Cooking");
        CookingTask task2 = new CookingTask("Washing");
        CookingTask task3 = new CookingTask("Cleaning");

        task1.start();
        task2.start();
        task3.start();

        System.out.println("All tasks started...");
    }
}

class CookingTask extends Thread {

    private String taskName;

    public CookingTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (true) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " - Running: " + taskName
            );

            // Wait 1 second before next iteration
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted.");
                break;
            }

            // Stop after 10 seconds
            if (System.currentTimeMillis() - startTime >= 10_000) {
                break;
            }
        }

        System.out.println(taskName + " finished.");
    }
}

Code : 

class FirstThread extends Thread {

    public FirstThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

class SecondThread extends Thread {

    public SecondThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

public class Thread {

    public static void main(String[] args) {

        System.out.println("----- Using run() -----");

        FirstThread t1 = new FirstThread("Thread 1");
        SecondThread t2 = new SecondThread("Thread 2");

        t1.run();
        t2.run();

        System.out.println("All tasks completed.");
    }
}
