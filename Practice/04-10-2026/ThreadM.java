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

public class ThreadM {

    public static void main(String[] args) {

        System.out.println("----- Using start() -----");

        FirstThread t1 = new FirstThread("Thread 1");
        SecondThread t2 = new SecondThread("Thread 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\n----- Using run() -----");

        FirstThread t3 = new FirstThread("Thread 3");
        SecondThread t4 = new SecondThread("Thread 4");

        t3.run();
        t4.run();
    }
}
