package co.edu.uniquindio.plataformalogistica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la Plataforma de Logística
 * Sistema de envíos urbanos same-day
 * 
 * @author Universidad del Quindío
 * @version 1.0
 */
public class PlataformaLogisticaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            PlataformaLogisticaApplication.class.getResource("/fxml/login.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Plataforma de Logística - Universidad del Quindío");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}