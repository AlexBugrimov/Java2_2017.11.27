import java.io.File;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class FindTask extends Task<Void> {
    final private int SIZE = 200;
    final private ObservableList<String> items1;
    final private String path;
    final private String end;
    LinkedList<String> list;

    public FindTask(ObservableList<String> items, String path, String end) {
        this.items1 = items;
        this.path = path;
        this.end = end;
        this.list = new LinkedList<>();
    }

//    //only in synk version
//    public void doWork() {
//        File file=new File(path);
//        if (file.isDirectory()) findIn(file);
//    }

    private void findIn(File fi) {
        File[] files = fi.listFiles((f) -> f.getName().endsWith(end) || f.isDirectory());

        if (isCancelled()) {
            return;
        }

        if (files != null && list.size() < SIZE)
            for (File file : files)
                if (file.isDirectory()) {
                    findIn(file);
                } else {
                    list.add(file.getAbsolutePath());
                }

        updateProgress(list.size(), SIZE);
    }

    @Override
    protected Void call() throws Exception {
        File file = new File(path);
        if (file.isDirectory()) {
            findIn(file);
        }
        return null;
    }

    @Override
    protected void cancelled() {
        items1.add("canceled by user...");
        updateScene();
    }

    private void updateScene() {
        updateProgress(SIZE, SIZE);
    }

    @Override
    protected void succeeded() {
        items1.addAll(list);
        items1.add("finded " + items1.size() + "files");
        updateScene();
    }
}
