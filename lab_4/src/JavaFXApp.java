import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GridPane.fxml"));
        Scene scene = new Scene(root);
        
//        Scene scene = new Scene(createPane(), 550, 550);
        stage.setTitle("Concurrency in JavaFX");
        stage.setScene(scene);
        stage.show();
    }
    private void btnFindClick(Event ev){
        System.out.println("Find!");
    }
    private void btnCancelClick(Event ev){
        System.out.println("Try to Cancel!"); 
    }

}
