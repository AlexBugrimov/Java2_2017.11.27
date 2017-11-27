package pro.bugrim;

public class CancelDemo implements Runnable {

    private Thread thread;
    private int data;
    private volatile boolean canWork = true;    // Данная будут меняться в обязательном порядке

    public CancelDemo(int data, String name) {
        this.data = data;
        this.thread = new Thread(this, name);
    }

    public void start() {
        thread.start();
    }

    public void interrupt() {
        thread.interrupt();
    }

    public void stop() {
        canWork = false;
    }

    public void join(int millSec) throws InterruptedException {
        thread.join(millSec);
    }

    @Override
    public void run() {
//        while (true) {
//            data++;
//            System.out.println("data = " + data);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//                break;
//            }
//        }

        while (canWork) {
            data++;
            System.out.println("current data = " + data);
        }
    }
}
