import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button button1 = new Button("Hello World");

        button1.setOnAction(event -> {
            System.out.println("Hello");
        });

        Label label = new Label("CSS is cool!");
        ObservableList pchData = FXCollections.observableArrayList(
                new PieChart.Data("Data 1", 20),
                new PieChart.Data("Data 2", 10),
                new PieChart.Data("Data 3", 50)
        );

        PieChart pieChart = new PieChart(pchData);

        Button button2 = new Button("Add data");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Random random = new Random();
                pchData.add(new PieChart.Data(("Data " + (pchData.size() + 1)),
                        10 + random.nextInt(20)));
            }
        });

        Button button3 = new Button("Rotate");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Animation animation = new Transition() {

                    {
                        setCycleDuration(Duration.seconds(2));  // Длительность анимации
                    }

                    @Override
                    protected void interpolate(double frac) {
                        pieChart.setRotate(frac * 360);
                    }
                };
                animation.play();
            }
        });


        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.setVgap(10);

        root.getChildren().add(button1);
        root.getChildren().add(label);
        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(pieChart);

        Scene scene = new Scene(root, 450, 500);
        scene.getStylesheets().add(getClass().getResource("CascadeSS.css").toExternalForm());
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
