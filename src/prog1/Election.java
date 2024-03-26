package prog1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote(int numVotes) {
        votes += numVotes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}

public class Election extends Application {
    private List<Candidate> candidates;
    private Map<Candidate, Integer> votes;
    private List<TextField> candidateNameFields;
    private List<TextField> rankFirstFields;
    private List<TextField> rankSecondFields;
    private List<TextField> rankThirdFields;
    private Button submitButton;
    private int[] numPlayers;

    @Override
    public void start(Stage primaryStage) {
    	
        candidates = new ArrayList<>();
        votes = new HashMap<>();
        candidateNameFields = new ArrayList<>();
        rankFirstFields = new ArrayList<>();
        rankSecondFields = new ArrayList<>();
        rankThirdFields = new ArrayList<>();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label numPlayersLabel = new Label("Enter the number of players:");
        TextField numPlayersField = new TextField();
        gridPane.add(numPlayersLabel, 0, 0);
        gridPane.add(numPlayersField, 1, 0);

        submitButton = new Button("Submit");
        gridPane.add(submitButton, 1, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Election");
        primaryStage.show();

         numPlayers = new int[1];

        submitButton.setOnAction(event -> {
            numPlayers[0] = Integer.parseInt(numPlayersField.getText());
            for (int i = 0; i < numPlayers[0]; i++) {
                TextField nameField = new TextField();
                candidateNameFields.add(nameField);
                gridPane.add(new Label("Enter Player " + (i + 1) + "'s name:"), 0, i + 2);
                gridPane.add(nameField, 1, i + 2);
                
                
            }
            Button startVotingButton = new Button("Start Voting");
            gridPane.add(startVotingButton, 1, numPlayers[0] + 2);

            startVotingButton.setOnAction(startEvent -> {
                for (int i = 0; i < numPlayers[0]; i++) {
                    String name = candidateNameFields.get(i).getText();
                    if (!name.isEmpty()) {
                        Candidate candidate = new Candidate(name);
                        candidates.add(candidate);
                    } else {
                        return;
                    }
                }
                gridPane.getChildren().removeAll(startVotingButton);
                showVotingScene(primaryStage);
            });
        });
    }

    private void showVotingScene(Stage primaryStage) {
    	
        GridPane votingGridPane = new GridPane();
       
        votingGridPane.setAlignment(Pos.CENTER);
        votingGridPane.setHgap(10);
        votingGridPane.setVgap(10);
        votingGridPane.setPadding(new Insets(20));

        int rowNum = 0;


       
        Label descriptionLabel = new Label("Enter votes for each category:");
        votingGridPane.add(descriptionLabel, 0, rowNum, 4, 1);

        rowNum++;
        Label tournamentLabel = new Label("tournament:");
        Label MVPLabel = new Label("MVP:");
        Label hoursLabel = new Label("hours:");

        votingGridPane.add(tournamentLabel, 1, rowNum);
        votingGridPane.add(MVPLabel, 2, rowNum);
        votingGridPane.add(hoursLabel, 3, rowNum);

        rowNum++;
        
        
        for (Candidate candidate : candidates) {
            Label nameLabel = new Label(candidate.getName() + ":");
            TextField rankFirstField = new TextField();
            TextField rankSecondField = new TextField(); 
            TextField rankThirdField = new TextField(); 


            votingGridPane.add(nameLabel, 0, rowNum);
            votingGridPane.add(rankFirstField, 1, rowNum);
            votingGridPane.add(rankSecondField, 2, rowNum);
            votingGridPane.add(rankThirdField, 3, rowNum);

            rankFirstFields.add(rankFirstField); 
            rankSecondFields.add(rankSecondField); 
            rankThirdFields.add(rankThirdField); 

            rowNum++;
        }

        Button calculateButton = new Button("Calculate");
        votingGridPane.add(calculateButton, 1, rowNum + 1);

        Scene votingScene = new Scene(votingGridPane, 400, 200);
        primaryStage.setScene(votingScene);

        calculateButton.setOnAction(event -> {
        	for (int i = 0; i < numPlayers[0]; i++) {
                int totalVotes = Integer.parseInt(rankFirstFields.get(i).getText()) * 5 +
                        Integer.parseInt(rankSecondFields.get(i).getText()) / 2 +
                        10000 / Integer.parseInt(rankThirdFields.get(i).getText());
                candidates.get(i).setVotes(totalVotes);
            }
            showResults(primaryStage); 
        });
    }

    private void showResults(Stage primaryStage) {
    	
        GridPane resultsGridPane = new GridPane();
        
        resultsGridPane.setAlignment(Pos.CENTER);
        resultsGridPane.setHgap(10);
        resultsGridPane.setVgap(10);
        resultsGridPane.setPadding(new Insets(20));

        
        Label resultsLabel = new Label("Election Results:");
        resultsGridPane.add(resultsLabel, 0, 0);

        int rowNum = 1;
        for (Candidate candidate : candidates) {
        	
            Label nameLabel = new Label(candidate.getName() + ":");
            Label votesLabel = new Label(String.valueOf(candidate.getVotes()));

            resultsGridPane.add(nameLabel, 0, rowNum);
            resultsGridPane.add(votesLabel, 1, rowNum);

            rowNum++;
        }

        Scene resultsScene = new Scene(resultsGridPane, 400, 200);
        primaryStage.setScene(resultsScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
