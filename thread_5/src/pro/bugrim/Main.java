package pro.bugrim;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; monitor.x < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);

                synchronized (monitor) {
                    monitor.x++;
                    monitor.notify();
                }
            }
        }, "First");

        Thread thread2 = new Thread(() -> {
            synchronized (monitor) {
                while (monitor.x < 50) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (int i = 0; monitor.x < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                synchronized (monitor) {
                    monitor.x++;
                }
            }
        }, "Second");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("x = " + monitor.x);
    }
}

class Monitor {
    volatile int x;
}
