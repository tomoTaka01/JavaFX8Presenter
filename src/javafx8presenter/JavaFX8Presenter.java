package javafx8presenter;

import java.io.IOException;
import java.net.URL;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * よなよなJavaFXでのLT.
 * 
 * @author tomo
 */
public class JavaFX8Presenter extends Application {
    public static final double WIDTH = 800.0;
    public static final double HEIGHT = 600.0;
    
    private   String[] pages = {
            "page1_Title.fxml"
            , "page2_intro.fxml"
            , "page3_sample.fxml"
            , "page4_calendar.fxml"
            , "page5_fxml.fxml"
            , "page6_ResourceBundle.fxml"
            , "page7_calendar.fxml"
            , "page9_refer.fxml"
            , "page8_demo.fxml"
    };
    private int pageIx;
    private Group root;
    private PageController presentController;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);
        root = new Group();
        root.setOnMouseClicked((t) -> {
            if (!presentController.doAction()){
                try {
                    goForward();
                } catch (IOException ex) {}
            }
        });
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        goForward();
    }

    private void goForward() throws IOException{
        URL url = getClass().getResource(pages[pageIx]);
        FXMLLoader loader = new FXMLLoader(url);
        Node next = (Node) loader.load();
        root.getChildren().add(next);   
        
        presentController = (PageController) loader.getController();

        Node present = null;
        if (root.getChildren().size() > 1) {
            present = root.getChildren().get(0);
        }
        translatePage(next, present);
        pageIx++;
        if (pageIx >= pages.length) {
            pageIx = 0;
        }
    }
    private void translatePage(Node next, final Node present){
        TranslateTransition slidein = new TranslateTransition(new Duration(1000));
        slidein.setNode(next);
        slidein.setFromX(WIDTH);
        slidein.setToX(0);
        slidein.play();
        if (present != null){
            TranslateTransition slideout = new TranslateTransition(new Duration((1000)));
            slideout.setNode(present);
            slideout.setToX(-WIDTH);
            slideout.setOnFinished((t) -> {
                root.getChildren().remove(present);
            });
            slideout.play();
        }
                
    }

    public static void main(String[] args) {
        launch(args);
    }
}
