package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.Candidate;
import data.UserDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.PlayerType;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class ResultsScreen {
    public static void show(Stage primaryStage, List<Candidate> candidates, String username, Candidate selectedCandidate) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Election Results");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

       
        Map<PlayerType, Candidate> winnersMap = new HashMap<>();

      
        for (Candidate candidate : candidates) {
            PlayerType type = candidate.getType();
            if (!winnersMap.containsKey(type)) {
                winnersMap.put(type, candidate);
            } else {
                Candidate currentWinner = winnersMap.get(type);
                if (candidate.getVotes() > currentWinner.getVotes()) {
                    winnersMap.put(type, candidate);
                }
            }
        }

     
        VBox resultsBox = new VBox(10);
        resultsBox.setAlignment(Pos.CENTER);

        for (Map.Entry<PlayerType, Candidate> entry : winnersMap.entrySet()) {
            PlayerType type = entry.getKey();
            Candidate winner = entry.getValue();
            Label winnerLabel = new Label("Winner for " + type + ": " + winner.getName() + " with " + winner.getVotes() + " votes");
            resultsBox.getChildren().add(winnerLabel);
        }

        Button loginButton = new Button("Go to Login Screen");
        loginButton.setOnAction(event -> {
            if (selectedCandidate != null) {
                UserDatabase.setSelectedCandidate(username, selectedCandidate);
                System.out.println(username + " voted for: " + selectedCandidate.getName());
            } else {
                System.out.println(username + " did not vote.");
            }
            UserDatabase.printDatabase();
            LoginScreen loginScreen = new LoginScreen(primaryStage);
            primaryStage.setScene(new Scene(loginScreen, 400, 200));
            primaryStage.setTitle("Login");
            primaryStage.show();
        });

        root.getChildren().addAll(titleLabel, resultsBox, loginButton);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Election Results");
        primaryStage.show();
    }
}
