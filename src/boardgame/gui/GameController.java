package boardgame.gui;

import boardgame.dbconnector.ConnectionProperties;
import boardgame.gamestate.BoardGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GameController {
    public String playerName1 = LaunchController.name1;

    public String playerName2 = LaunchController.name2;

    public String winnerName;

    String timeInText;

    BoardGame boardGame;

    public LocalDateTime time;

    public Image img;

    public GridPane gameGrid;

    public Label dominoesLabel;

    public Label dtLabel;

    public Label messageLabel;

    public Button resetButton;

    public Button finishButton;

    public Label winnerLabel;

    public Label nextPlayerLabel;

    int row, col;

    public void initialize()
    {
        resetGame();
    }

    public void resetGame()
    {
        boardGame = new BoardGame();
        displayGameState();
        messageLabel.setText(playerName1 + " vs " + playerName2);
        nextPlayerLabel.setText(playerName1);
        dominoesLabel.setText("0");
        time = LocalDateTime.now();
        timeInText = time.toString();
        dtLabel.setText(timeInText);
        winnerLabel.setText("    ");
        if(boardGame.isFinishedHorizontal())
        {
            winnerLabel.setText("Wrong Start");
        }
    }

    public void displayGameState() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                StackPane stackPane = (StackPane) gameGrid.getChildren().get(i * 8 + j);
                ImageView view = (ImageView) stackPane.getChildren().get(0);
                if(boardGame.cellsState[i][j]==0)
                {
                    img = new Image(getClass().getResource("/resources/images/empty.png").toExternalForm());
                }
                else
                {
                    img = new Image(getClass().getResource("/resources/images/domino.png").toExternalForm());
                }
                view.setImage(img);
            }
        }
    }

    public void handleClickOnCell(MouseEvent mouseEvent) throws NullPointerException {
        row = GridPane.getRowIndex((Node) mouseEvent.getSource());
        col = GridPane.getColumnIndex((Node) mouseEvent.getSource());

        boardGame.clicked(row, col);
        dominoesLabel.setText(String.valueOf(boardGame.numberOfDominoes()));
        if(boardGame.numberOfDominoes()%2 == 0)
            nextPlayerLabel.setText(playerName1);
        else
            nextPlayerLabel.setText(playerName2);

        if(boardGame.isFinishedVertical())
        {
            winnerName = playerName1;
            winnerLabel.setText(playerName1);
            gameGrid.setDisable(true);
        }
        if(boardGame.isFinishedHorizontal())
        {
            winnerName = playerName2;
            winnerLabel.setText(playerName2);
            gameGrid.setDisable(true);
        }

        displayGameState();
    }


    public void handleResetButton(ActionEvent actionEvent)  {
        resetGame();
    }

    public void handleFinishButton(ActionEvent actionEvent) throws IOException {
        ConnectionProperties cp = new ConnectionProperties();
        try {
            cp.insertRecord(timeInText, playerName1 + " vs " + playerName2, winnerName, boardGame.numberOfDominoes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/lastgames.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        resetGame();
    }
}
