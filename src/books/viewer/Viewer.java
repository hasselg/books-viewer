/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package books.viewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author hasselg
 */
public class Viewer extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Books!");
        
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://localhost:8080/books/library/");
        
        StackPane root = new StackPane();
        root.getChildren().add(browser);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
