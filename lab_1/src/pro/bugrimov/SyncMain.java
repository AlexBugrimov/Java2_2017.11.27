package pro.bugrimov;

class Data {
    volatile int count = 0;
    volatile static int countStatic = 0;
}

class MyThread implements Runnable {
    private final Data obj;

    MyThread(Data data) {
        obj = data;
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
            Data.countStatic++;
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
        Data data = new Data();

        new MyThread(data);
        new MyThread(data);

        System.out.println(data.count);
        System.out.println(Data.countStatic);
    }
}
