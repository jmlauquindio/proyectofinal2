package co.edu.uniquindio.plataformalogistica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la pantalla de login
 * RF-001: Registrarse y/o iniciar sesión
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuración inicial del controlador
        setupUI();
    }

    private void setupUI() {
        // Configurar tooltips o validaciones iniciales si es necesario
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Validación básica de email
        if (!isValidEmail(email)) {
            showAlert("Error", "Por favor, ingrese un email válido.", Alert.AlertType.WARNING);
            return;
        }

        // Aquí iría la lógica de autenticación
        // Por ahora, simulamos usuarios de prueba
        if (authenticateUser(email, password)) {
            showAlert("Éxito", "Inicio de sesión exitoso!\n\nEsta es una versión de demostración.", Alert.AlertType.INFORMATION);
            // Aquí cargaríamos la ventana principal
        } else {
            showAlert("Error", "Credenciales incorrectas.\n\nUsuarios de prueba:\n• admin@uniquindio.edu.co / admin123\n• usuario@email.com / user123", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleRegister(ActionEvent event) {
        showAlert("Registro", "Funcionalidad de registro en desarrollo.\n\nEsta versión incluye usuarios de prueba preconfigurados.", Alert.AlertType.INFORMATION);
    }

    /**
     * Validación básica de email
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    /**
     * Autenticación simulada con usuarios de prueba
     */
    private boolean authenticateUser(String email, String password) {
        // Usuarios de prueba
        return (email.equals("admin@uniquindio.edu.co") && password.equals("admin123")) ||
               (email.equals("usuario@email.com") && password.equals("user123"));
    }

    /**
     * Muestra alertas al usuario
     */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}