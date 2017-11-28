package ru.bugrim;

public class ThreadThree extends Thread {
    private Object monitor;

    public ThreadThree(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < TicTakToi.num; i++) {
                System.out.println(" - 3");
                synchronized (monitor) {
                    monitor.notify();
                    if (i < TicTakToi.num - 1) {
                        monitor.wait();
                    }
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
