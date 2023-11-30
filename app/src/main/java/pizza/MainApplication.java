package pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that runs the application
 * @author Mohammed Salama, Dakshal Panicker
 */
public class  MainApplication extends Application {
    /**
     * Start the application
     * @param stage inputted stage
     * @throws IOException the exception thrown
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(MainApplication.class.getResource(
                        "main-menu.fxml"
                        ));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(680);
        stage.setMinHeight(700);
    }
    public static void main(String[] args) {
        launch();
    }
}