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
