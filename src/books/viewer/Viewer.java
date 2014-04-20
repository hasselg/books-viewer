package books.viewer;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author hasselg
 */
public class Viewer extends Application {

    private WebView browser;
    private ProgressIndicator progressIndicator;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setSecurityManager(null);
        launch(args);
    }

    private void initComponents() {
        // Create the web viewer
        browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://localhost:8080/books/library/");
        Worker<Void> loadWorker = webEngine.getLoadWorker();

        // Create the progress indicator
        progressIndicator = new ProgressIndicator(0);

        // Bind the progress indicator to the WebEngine's progress property
        progressIndicator.progressProperty().bind(loadWorker.progressProperty());
    }

    private Parent getScene() {
        // Setup the center pane
        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(browser);

        // Setup the bottom pane
        HBox bottomPane = new HBox();
        bottomPane.setAlignment(Pos.BOTTOM_RIGHT);
        bottomPane.getChildren().add(progressIndicator);

        // Populate the main pane
        BorderPane root = new BorderPane();
        root.setCenter(centerPane);
        root.setBottom(bottomPane);

        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        initComponents();

        primaryStage.setTitle("Books!");

        // Populate the scene
        Parent rootScene = getScene();
        primaryStage.setScene(new Scene(rootScene, 800, 600));
        primaryStage.show();
    }
}
