package pro.bugrim;

import static java.lang.System.out;

public class Main {

    private static final int COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    out.println(Thread.currentThread().getName());
                    test.increment();
                }
            }
        };

        Monitor monitor = new Monitor();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (monitor) {
                        monitor.x++;
                    }
                }
            }
        };

        Thread[] threads = new Thread[COUNT];

        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(runnable2, "Adder - " + (i + 1));
            threads[i].start();
        }

        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }

        out.println(test.getValue());
        out.println(monitor.x);
    }
}

class Test {

    private int value;

    int getValue() {
        return value;
    }

    synchronized void increment() {
        value++;
    }
}

class Monitor {
    public int x;
}
