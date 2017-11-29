package pro.bugrim;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] array = generateArray();
        int checkSum;
        long t1 = System.currentTimeMillis();
        checkSum = countArraySum(array);
        long t2 = System.currentTimeMillis();

        int[] results = new int[ThreadSumConst.THREADS_NUMBER];

        Thread[] threads = new Thread[ThreadSumConst.THREADS_NUMBER];

        for (int i = 0; i < ThreadSumConst.THREADS_NUMBER; i++) {
            threads[i] = new ThreadSum(results, array, i, ThreadSumConst.THREADS_NUMBER);
        }

        long t3 = System.currentTimeMillis();

        for (int i = 0; i < ThreadSumConst.THREADS_NUMBER; i++) {
            threads[i].start();
        }

        for (int i = 0; i < ThreadSumConst.THREADS_NUMBER; i++) {
            threads[i].join();
        }

        int finalSum = countArraySum(results);
        long t4 = System.currentTimeMillis();

        System.out.println("check sum = " + checkSum + ", time = " + (t2-t1));
        System.out.println("thread sum = " + finalSum + ", time = " + (t4-t3));

    }

    private static int countArraySum(int[] array) {
        int sum = 0;
        for (int item : array) {
            sum += item;
        }
        return sum;
    }

    private static int[] generateArray() {
        int[] array = new int[ThreadSumConst.ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ThreadSumConst.ARRAY_SIZE; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}
