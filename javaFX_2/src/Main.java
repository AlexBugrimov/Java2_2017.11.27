import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Hello World");

        button.setOnAction(event -> {
            System.out.println("Hello");
        });

        Label label = new Label("CSS is cool!");
        ObservableList pchData = FXCollections.observableArrayList(
                new PieChart.Data("Data 1", 20),
                new PieChart.Data("Data 2", 10),
                new PieChart.Data("Data 3", 50)
        );

        PieChart pieChart = new PieChart(pchData);


        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.setVgap(10);

        root.getChildren().add(button);
        root.getChildren().add(label);
        root.getChildren().add(pieChart);

        Scene scene = new Scene(root, 450, 500);
        scene.getStylesheets().add(getClass().getResource("CascadeSS.css").toExternalForm());
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
