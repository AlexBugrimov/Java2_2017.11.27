package pro.bugrim;

class Data {
    volatile int count = 0;
    static int countSt = 0;
}

class MyThread implements Runnable {
    private final Data obj;

    MyThread(Data d) {
        obj = d;
        new Thread(this).start();
    }

    void Add() {
        synchronized (obj) {
            try {
                Thread.sleep(5);
                int n = obj.count;
                n++;
                Thread.sleep(5);
                obj.count = n;
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static void AddStatic() {
        synchronized (Data.class) {
            try {
                Thread.sleep(5);
                int n = Data.countSt;
                n++;
                Thread.sleep(5);
                Data.countSt = n;
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override

    public void run() {
        for (int i = 0; i < 10; i++) Add();
        for (int i = 0; i < 10; i++) AddStatic();
    }
}

public class SyncMain {

    public static void main(String[] args) throws Exception {
        Data d = new Data();
        MyThread t1 = new MyThread(d);
        MyThread t2 = new MyThread(d);

        Thread.sleep(3000);
        System.out.println(d.count);
        System.out.println(Data.countSt);
    }
}
