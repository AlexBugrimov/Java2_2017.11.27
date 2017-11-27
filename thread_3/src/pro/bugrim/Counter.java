package pro.bugrim;

public class Counter implements Runnable {

    private Thread thread;
    private int counter;
    private int steps;

    public Counter(int counter, int steps) {
        this.counter = counter;
        this.steps = steps;
        thread = new Thread(this);
        thread.start();
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {

        for (int i = 0; i < steps; i++) {
            counter++;
            Data.value++;
            Data.aValue.getAndIncrement();
        }
        System.out.println(Thread.currentThread().getName() + " local counter = " + counter + ", global counter = " + Data.value);
    }
}
