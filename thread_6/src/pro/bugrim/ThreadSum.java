package pro.bugrim;

public class ThreadSum extends Thread {

    private int[] results;
    private final int[] data;
    private final int pos;
    private final int step;

    public ThreadSum(int[] results, int[] data, int pos, int step) {
        this.results = results;
        this.data = data;
        this.pos = pos;
        this.step = step;
    }

    @Override
    public void run() {
        results[pos] = 0;
        for (int i = pos; i < ThreadSumConst.ARRAY_SIZE; i += step) {
            results[pos] += data[i];
        }
    }
}
