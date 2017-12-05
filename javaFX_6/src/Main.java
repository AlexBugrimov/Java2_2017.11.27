import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        Task<String> task = new MyTask();

        Thread thread = new Thread(task);

        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(50);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            System.out.println(bar.getProgress());
        }

//        thread.join();


//        System.out.println(task.get());
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

class MyTask extends Task<String> {

    @Override
    protected String call() throws Exception {
        int i;
        for (i = 0; i < 10; i++) {
            Thread.sleep(300);
            updateProgress(i + 1, 10);
        }
        return "Task done, i = " + i;
    }

    @Override
    protected void succeeded() {
        System.out.println("Task done!");;
    }
}
