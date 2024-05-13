package gui;

import data.UserDatabase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterScreen extends GridPane  {
    public RegisterScreen(Stage primaryStage) {
       
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

     
        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        registerButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

     
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserDatabase.register(username, password)) {
              
                System.out.println("User registered successfully:");
                UserDatabase.printDatabase();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Success");
            alert.setHeaderText(null);
            alert.setContentText("Registration successful!");
            alert.showAndWait();

     
            primaryStage.getScene().setRoot(new LoginScreen(primaryStage));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Error");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists. Please choose a different username.");
                alert.showAndWait();
            }
            });

        backButton.setOnAction(event -> {
       
            primaryStage.getScene().setRoot(new LoginScreen(primaryStage));
        });

    
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));
        add(usernameLabel, 0, 0);
        add(usernameField, 1, 0);
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        add(registerButton, 1, 2);
        add(backButton, 1, 3);
    }
}
