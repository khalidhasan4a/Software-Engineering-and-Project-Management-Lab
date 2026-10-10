import java.util.concurrent.atomic.AtomicLong;

public class Khalid_Thread {
    static AtomicLong safe = new AtomicLong();
    static long unsafe = 0;

    static class Counter extends Thread {
        int n;
        boolean mode;
        long count = 0;

        Counter(int n, boolean mode) {
            this.n = n;
            this.mode = mode;
        }

        public void run() {
            for (int i = 0; i < n; i++) {
                if (mode) safe.incrementAndGet();
                else unsafe++;
                count++;
            }
        }
    }

    public static void main(String[] a) throws Exception {
        if (a.length != 3) {
            System.out.println("Usage: java Khalid_Thread <threads> <increments> <true|false>");
            return;
        }

        int t, n;
        try {
            t = Integer.parseInt(a[0]);
            n = Integer.parseInt(a[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (t < 1 || n < 1 ||
            !(a[2].equalsIgnoreCase("true") || a[2].equalsIgnoreCase("false"))) {
            System.out.println("Invalid input.");
            return;
        }

        boolean mode = Boolean.parseBoolean(a[2]);

        safe.set(0);
        unsafe = 0;

        Counter[] c = new Counter[t];
        for (int i = 0; i < t; i++) {
            c[i] = new Counter(n, mode);
            c[i].start();
        }

        long total = 0;
        for (Counter x : c) {
            x.join();
            total += x.count;
        }

        long s = mode ? safe.get() : unsafe;
        long d = Math.abs(s - total);

        System.out.println("Expected: " + (long) t * n);
        System.out.println("Static: " + s);
        System.out.println("Non-static total: " + total);
        System.out.println("Difference: " + d);
        System.out.printf("Difference (%%): %.2f%%%n",
                total == 0 ? 0 : d * 100.0 / total);
    }
}
