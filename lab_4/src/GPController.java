import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GPController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnFindClick(ActionEvent event) {
        System.out.println("Find");
    }

    @FXML
    public void btnCancelClick() {
        System.out.println("Exit");
    }
}
