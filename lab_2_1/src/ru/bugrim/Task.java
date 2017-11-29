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
    private Object monitor;

    public ThreadOne(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    public ThreadOne start() {
        thread.start();
        return this;
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Task.lines; i++) {
                synchronized (monitor) {

                    while (Task.count == 1) {
                        System.out.print(1);
                        monitor.notifyAll();
                        Task.count++;
                    }
                    if (i < Task.lines - 1) {
                        Thread.sleep(100);

                        monitor.wait();
                    }


                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTwo implements Runnable {

    private Thread thread;
    private Object monitor;

    public ThreadTwo(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    public ThreadTwo start() {
        thread.start();
        return this;
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Task.lines; i++) {

                synchronized (monitor) {
                    while (Task.count == 2) {
                        System.out.print(" - " + 2);
                        monitor.notifyAll();
                        Task.count++;
                    }
                    if (i < Task.lines - 1) {
                        Thread.sleep(100);


                        monitor.wait();
                    }

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadThree implements Runnable {

    private Thread thread;
    private Object monitor;

    public ThreadThree(Object monitor) {
        this.monitor = monitor;
        this.thread = new Thread(this);
    }

    public ThreadThree start() {
        thread.start();
        return this;
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < Task.lines; i++) {

                synchronized (monitor) {
                    while (Task.count == 3) {
                        System.out.println(" - " + 3);
                        monitor.notifyAll();
                        Task.count = 1;
                    }
                    if (i < Task.lines - 1) {
                        Thread.sleep(100);

                        monitor.wait();
                    }

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}