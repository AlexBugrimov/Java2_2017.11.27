import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Hello World");
        TextField txt = new TextField();
        Label label = new Label();


        button.setOnAction(event -> {
            if ("".equals(txt.getText())) {
                label.setText("Hello World");
            } else {
                label.setText("Hello " + txt.getText() + "!");
            }
        });
        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(10);

        root.add(button, 0, 0);
        root.add(txt, 1, 0);
        root.add(label, 1, 1);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
