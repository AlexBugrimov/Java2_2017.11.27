package ru.bugrim;

public class ThreadOne extends Thread {
    private Object monitor;

    public ThreadOne(Object monitor) {
        this.monitor = monitor;
    }

    public void run() {
//        try {
            for (int i = 0; i < TicTakToi.num; i++) {
                System.out.print(1 + " - ");
                synchronized (monitor) {
                    monitor.notify();
                    if (i < TicTakToi.num - 1) {
//                        monitor.wait();
                    }
                }
            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
