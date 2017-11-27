package pro.bugrim;

public class EntryPoint {

    public static void main(String[] args) throws InterruptedException {
        CancelDemo demo = new CancelDemo(0, "CancelDemo");
        demo.start();
//        demo.interrupt();

        demo.join(1000);
        demo.stop();
        System.out.println("end of main");
    }
}
