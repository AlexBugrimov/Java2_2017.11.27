package ru.bugrim;

public class Task {
    static int lines = 20;
    static int count = 1;

    public static void main(String[] args) {
        Object monitor = new Object();
        ThreadOne one = new ThreadOne(monitor).start();
        ThreadTwo two = new ThreadTwo(monitor).start();
        ThreadThree three = new ThreadThree(monitor).start();

        one.join();
        two.join();
        three.join();
    }
}

class ThreadOne implements Runnable {

    private Thread thread;
    private final Object monitor;

    ThreadOne(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    ThreadOne start() {
        thread.start();
        return this;
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (Task.count == 1) {
                synchronized (monitor) {
                    for (int i = 0; i < Task.lines; i++) {
                       System.out.print(1);
                       Task.count++;
                        monitor.notifyAll();
                        if (i < Task.lines - 1) {
                            monitor.wait();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("---1--- " + e.getMessage());
        }
    }
}

class ThreadTwo implements Runnable {

    private Thread thread;
    private final Object monitor;

    ThreadTwo(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    ThreadTwo start() {
        thread.start();
        return this;
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (Task.count == 2) {

                synchronized (monitor) {
                    for (int i = 0; i < Task.lines; i++) {
                        System.out.print(" - " + 2);
                        Task.count++;
                        monitor.notifyAll();
                        if (i < Task.lines - 1) {
                            monitor.wait();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("---2---" + e.getMessage());
        }
    }
}

class ThreadThree implements Runnable {

    private Thread thread;
    private final Object monitor;

    ThreadThree(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    ThreadThree start() {
        thread.start();
        return this;
    }

    void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (Task.count == 3) {

                synchronized (monitor) {
                    for (int i = 0; i < Task.lines; i++) {
                        System.out.println(" - " + 3);
                        Task.count = 1;
                        monitor.notifyAll();
                        if (i < Task.lines - 1) {
                            monitor.wait();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("---3---" + e.getMessage());
        }
    }
}