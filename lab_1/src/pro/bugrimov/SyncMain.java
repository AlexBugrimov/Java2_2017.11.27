package pro.bugrimov;

class Data {
    volatile int count = 0;
    volatile static int countSt = 0;
}

class MyThread implements Runnable {
    private final Data obj;

    MyThread(Data d) {
        obj = d;
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        synchronized (obj) {
            obj.count++;
        }
    }

    private static void addStatic() {
        synchronized (Data.class) {
            Data.countSt++;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            add();
        }

        for (int i = 0; i < 10; i++) {
            addStatic();
        }
    }
}

public class SyncMain {

    public static void main(String[] args) throws Exception {
        Data d = new Data();
        MyThread t1 = new MyThread(d);
        MyThread t2 = new MyThread(d);

        System.out.println(d.count);
        System.out.println(Data.countSt);
    }
}
