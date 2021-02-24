package boardgame.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LaunchController {

    public TextField playerName1TextField;

    public TextField playerName2TextField;

    public Label errorLabel1;

    public Label errorLabel2;

    static String name1;
    static String name2;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (playerName1TextField.getText().isEmpty() && playerName2TextField.getText().isEmpty()) {
            errorLabel1.setText("Enter first player name");
            errorLabel2.setText("Enter second player name");
        } else if (playerName1TextField.getText().isEmpty() && !playerName2TextField.getText().isEmpty()) {
            errorLabel1.setText("Enter first player name");
            errorLabel2.setText("");
        } else if (!playerName1TextField.getText().isEmpty() && playerName2TextField.getText().isEmpty()) {
            errorLabel1.setText("");
            errorLabel2.setText("Enter second player name");
        }
        else
        {
            name1 = playerName1TextField.getText();
            name2 = playerName2TextField.getText();
            errorLabel1.setText("");
            errorLabel2.setText("");
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/game.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
