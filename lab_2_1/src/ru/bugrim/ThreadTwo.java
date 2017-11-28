package ru.bugrim;

public class ThreadTwo implements Runnable {
    private Thread thread;
    private Object monitor;

    public Thread getThread() {
        return thread;
    }

    public ThreadTwo(Object monitor) {
        thread = new Thread(this);
        this.monitor = monitor;
    }

    public void run() {
        try {
//            Thread.sleep(1);
            for (int i = 0; i < TicTakToi.num; i++) {
                System.out.print(2);
                synchronized (monitor) {
                    monitor.notify();
                    if (i < TicTakToi.num - 1) {
                        monitor.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
