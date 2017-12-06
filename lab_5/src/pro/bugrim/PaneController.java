package pro.bugrim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PaneController implements Initializable {

    public ListView listView;
    public Label labelPath;
    public Button buttonOpen;
    public Button buttonSaveToDB;
    private final FileChooser fileChooser = new FileChooser();
    private String path;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tuneFileChooser();
    }

    @FXML
    public void openImage(ActionEvent event) {
        File file = fileChooser.showOpenDialog(getWindow(event));
        if (file != null) {
            path = file.getAbsolutePath();
            labelPath.setText(path);
        }
    }

    @FXML
    public void saveInDb(ActionEvent event) {

    }

    @FXML
    public void createDatabase(ActionEvent event) {

    }

   /**
     * Настроить загрузчик файлов.
     */
    private void tuneFileChooser() {
        fileChooser.setTitle("Загрузить файл");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображение PNG (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("Изображение JPG (*.jpg, *jpeg)", "*.jpg", "*.jpeg")
        );
    }

    /**
     * Получить окно.
     * @param event Событие.
     * @return Текущее окно.
     */
    private Window getWindow(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        return scene.getWindow();
    }
}
