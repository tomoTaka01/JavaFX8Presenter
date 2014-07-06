package javafx8presenter;

import java.io.IOException;
import java.net.URL;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author tomo
 */
public class JavaFX8Presenter extends Application {
    public static final double WIDTH = 800.0;
    public static final double HEIGHT = 600.0;
    
    private   String[] pages = {"page1_Title.fxml"
            , "page2_intro.fxml"
            , "page3_sample.fxml"
            , "page4_calendar.fxml"
            , "page5_fxml.fxml"
            , "page6_ResourceBundle.fxml"
            , "page7_calendar.fxml"
            , "page8_demo.fxml"
            , "page9_refer.fxml"
    };
    private int pageIx;
    private Group root;
    private PageController presentController;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);
        root = new Group();
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (!presentController.doAction()){
                    try {
                        goForward();
                    } catch (IOException ex) {}                    
                }
            }
        });
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        goForward();
    }

    private void goForward() throws IOException{
        URL url = getClass().getResource(pages[pageIx]);
        System.out.println("***" + url);
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
            slideout.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    root.getChildren().remove(present);
                }
            });
            slideout.play();
        }
                
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
