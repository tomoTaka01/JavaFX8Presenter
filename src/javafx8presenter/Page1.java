package javafx8presenter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * Title.
 * 
 * @author tomo
 */
public class Page1 implements PageController, Initializable {

    @FXML
    Text p1;
    @FXML
    Text p2;

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
