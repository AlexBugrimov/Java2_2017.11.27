package pro.bugrim;

import static java.lang.System.out;

public class SimpleThread extends Thread {

    @Override
    public void run() {
        out.println("Thread started working...");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            out.print(ex.getMessage());
        }
        out.println("Thread name: " + getName());
    }
}
