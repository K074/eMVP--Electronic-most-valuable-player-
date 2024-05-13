package logic; 

import javafx.scene.control.Alert;


public interface Voter {
    void vote(Candidate candidate);

    default void showVoteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Your vote has been cast successfully!");
        alert.showAndWait();
    }
}
