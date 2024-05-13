package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Candidate;
import logic.Voter;
import data.UserDatabase;

import java.util.List;
import java.util.Map;

public class Election extends Application implements Voter {
    private List<Candidate> candidates;
    private Button submitButton;
    private Candidate selectedCandidate;
    private Map<String, Candidate> userVotes;
    private String username;
    
    @Override
    public void start(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen(primaryStage);
        primaryStage.setScene(new Scene(loginScreen, 400, 200));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    
    private void castVote(String username, Candidate candidate) {
        UserDatabase.setSelectedCandidate(username, candidate);
        updateCandidateVotes();
    }
    
    public void vote(Candidate candidate) {
        castVote(username, candidate);
        showVoteConfirmation();
        updateCandidateVotes();        
    }
    // implicitnej implementácie metód v rozhraniach 
    private void updateCandidateVotes() {
        for (Candidate candidate : candidates) {
            candidate.resetVotes();
        }

        for (Candidate candidate : candidates) {
            for (Map.Entry<String, Candidate> entry : userVotes.entrySet()) {
                if (entry.getValue() == candidate) {
                    candidate.incrementVotes(0);
                }
            }
        }
    }
    //observer
    
    public void showMainScreen(Stage primaryStage, String username) {
        candidates = Candidate.getCandidates();
        userVotes = UserDatabase.userVotes;

        HBox playerPaneContainer = new HBox(20);
        for (Candidate candidate : Candidate.getCandidates()) {
            PlayerPane playerPane = new PlayerPane(candidate);
            playerPaneContainer.getChildren().add(playerPane);
        }

        ScrollPane scrollPane = new ScrollPane(playerPaneContainer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        submitButton = new Button("Vote");
        submitButton.setOnAction(event -> {
            castVote(username, ((PlayerPane) playerPaneContainer.getChildren().get(0)).getCandidate());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Your vote has been cast successfully!");
            alert.showAndWait();
            updateCandidateVotes();
            ResultsScreen.show(primaryStage, Candidate.getCandidates(), username, selectedCandidate);
        });

        Button editButton = new Button("Edit Candidates");
        editButton.setOnAction(event -> {
            if (UserDatabase.isAdminMode()) {
                showEditorScreen(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Access Denied");
                alert.setHeaderText(null);
                alert.setContentText("You need to be logged in as an administrator to access this feature.");
                alert.showAndWait();
            }
        });

        HBox buttonPane = new HBox(submitButton, editButton);
        buttonPane.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(scrollPane, buttonPane);

        primaryStage.setScene(new Scene(root, 800, 200));
        primaryStage.setTitle("Election Voting");
        primaryStage.show();

        Candidate previousVote = userVotes.get(username);
        if (previousVote != null) {
            
        }
    }
    
    private CandidateEditorPane showEditorScreen(Stage primaryStage) {
        CandidateEditorPane editorPane = new CandidateEditorPane();
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().add(editorPane);

        Scene editorScene = new Scene(root, 400, 200);
        primaryStage.setScene(editorScene);
        primaryStage.setTitle("Candidate Editor");
        primaryStage.show();
        
        return editorPane;
    }
  
    public static void main(String[] args) {
        launch(args);
    }
}
