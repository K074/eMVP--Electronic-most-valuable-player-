package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import logic.Candidate;
import logic.Skill;


public class PlayerPane extends VBox {
    private Candidate candidate;
    private Button voteButton;
    private Button abandonButton;
    private Skill skill;
    

    public PlayerPane(Candidate player) {
        this.candidate = player;
        this.skill = new Skill();
        

        voteButton = new Button("Vote for " + player.getName());
        voteButton.setOnAction(event -> voteForPlayer());

        abandonButton = new Button("Abandon for " + player.getName());
        abandonButton.setOnAction(event -> abandonVote());

        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(voteButton, abandonButton);

        setSpacing(1);
        setPadding(new Insets(10));

        Label nameLabel = new Label(candidate.getName());
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        Label typeLabel = new Label(candidate.getType().toString());
        Label votesLabel = new Label("Votes: " + candidate.getVotes());

        getChildren().addAll(
                nameLabel,
                typeLabel,
                votesLabel,
                buttonsBox
        );
    }

    private void voteForPlayer() {
        candidate.incrementVotes(1);
        voteButton.setText("Voted");
        voteButton.setDisable(true);
        abandonButton.setDisable(false); 
        skill.SkillCalculator();
    }

    private void abandonVote() {
        candidate.deincrementVotes(1);
        abandonButton.setText("Abandoned");
        abandonButton.setDisable(true);
        voteButton.setDisable(false);
        skill.SkillCalculator();
    }

    public Candidate getCandidate() {
        return candidate;
    }
}
