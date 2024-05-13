package gui;

import data.UserDatabase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Candidate;


public class LoginScreen extends GridPane {
	
    private TextField usernameField;
    private PasswordField passwordField;
    private Candidate selectedCandidate;
    
    private UserDatabase userDatabase = UserDatabase.getInstance();

    public LoginScreen(Stage primaryStage) {
 
    	 
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        
        Label titleLabel = new Label("eMVP (Electronic most valuable player)");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> primaryStage.close());
        add(titleLabel, 0, 0, 5, 1);
        add(usernameLabel, 2, 2);
        add(usernameField, 3, 2);
        add(passwordLabel, 2, 3);
        add(passwordField, 3, 3);
        add(loginButton, 3, 4);
        add(exitButton, 4, 5);

        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        loginButton.getStyleClass().add("button");
        registerButton.getStyleClass().add("button");
        System.out.println("userDatabase "+ userDatabase);

        
       
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

         
            if (UserDatabase.login(username, password)) {
            	 Candidate previousVote = UserDatabase.getSelectedCandidate(username);
            	
            	Election election = new Election();
            	UserDatabase.setSelectedCandidate(username, selectedCandidate);
                election.showMainScreen(primaryStage, username);
                
                if (previousVote != null) {
                	
//                    election.setSelectedCandidate(previousVote);
                }
                
            	 
            } else {
            	 clearErrorLabels();
            	 Alert alert = new Alert(Alert.AlertType.ERROR);
            	 alert.setTitle("Login Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Invalid username or password");
                 alert.showAndWait();
                passwordField.clear();
            }          
        });
        
        registerButton.setOnAction(event -> {
        	
            RegisterScreen registerScreen = new RegisterScreen(primaryStage);
            primaryStage.getScene().setRoot(registerScreen);
        });
        
        add(registerButton, 3, 5);
       
    }
    
    private void clearErrorLabels() {
        getChildren().removeIf(node -> node.getStyleClass().contains("error-label"));
    }
}


