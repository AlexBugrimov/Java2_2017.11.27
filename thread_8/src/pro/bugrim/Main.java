package pro.bugrim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorService service = Executors.newWorkStealingPool();

        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            results.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(600);
                    System.out.println(Thread.currentThread().getName() + " - done");
                    return Thread.currentThread().getName();
                }
            }));
        }

        service.shutdown();

        for (Future<String> result : results) {
            System.out.println("end result: " + result.get());
        }
    }
}
