package pro.bugrim;

import static java.lang.System.out;

public class RunnableThread implements Runnable {

    private Thread thread;

    RunnableThread() {
        this.thread = new Thread(this, "RunnableThread");
    }

    void runAndWait() throws InterruptedException {
        thread.start();
        thread.join();
    }

    @Override
    public void run() {
        out.println("Name thread: " + Thread.currentThread().getName());
    }
}
