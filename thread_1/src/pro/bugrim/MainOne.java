package pro.bugrim;

import static java.lang.System.out;

public class MainOne {

    public static void main(String[] args) throws InterruptedException {
        SimpleThread thread1 = new SimpleThread();
        RunnableThread thread2 = new RunnableThread();

        thread1.start();
        thread1.join();
        thread2.runAndWait();

        out.println("Thread name: " + Thread.currentThread().getName());
    }
}
