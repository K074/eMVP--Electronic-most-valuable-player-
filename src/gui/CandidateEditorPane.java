package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Candidate;
import data.PlayerType;
import data.UserData;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class CandidateEditorPane extends VBox {
    private TextField nameField;
    private TextField typeField;
    private Label errorLabel;

    public CandidateEditorPane()  {
        Label titleLabel = new Label("Create a new candidate:");
        titleLabel.setStyle("-fx-font-weight: bold");

        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label typeLabel = new Label("Type:  ");
        typeField = new TextField();

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red");
        
        
        Button addButton = new Button("Add Candidate");
        addButton.setOnAction(event -> addCandidate());

        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titleLabel,
                new HBox(10, nameLabel, nameField),
                new HBox(10, typeLabel, typeField),
                addButton
        );
    }

    private void addCandidate() {

        String name = nameField.getText();
        String typeString = typeField.getText();

        if (!name.isEmpty() && !typeString.isEmpty()) {
            PlayerType type = null;
            
            try {
                type = PlayerType.valueOf(typeString);
            } catch (IllegalArgumentException e) {
            	Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter an existing type.");
                alert.showAndWait();
                return;
            }

         
            boolean typeExists = false;
            for (PlayerType existingType : PlayerType.values()) {
                if (existingType == type) {
                    typeExists = true;
                    break;
                }
            }

            if (!typeExists) {
            	 Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Please enter an existing type.");
                 alert.showAndWait();
                 return;
            }
            
            writeUserDataToFile(name);
            


            
            Candidate newCandidate = new Candidate(name, type);
            Candidate.getCandidates().add(newCandidate);
            
            
            nameField.clear();
            typeField.clear();
            
            
            Stage stage = (Stage) getScene().getWindow();
            Election election = new Election();
            election.showMainScreen(stage, "admin"); 
        }
    }
    
    private void writeUserDataToFile(String username) {
        try (FileOutputStream fileOut = new FileOutputStream( "userdata.txt", true);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            UserData userData = new UserData(username);
            objectOut.writeObject(userData);
            System.out.println("The UserData object has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

