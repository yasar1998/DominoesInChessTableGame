package boardgame.gui;

import boardgame.dbconnector.ConnectionProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class LastGamesController {
    public TableView<GameResult> GamesTable;

    public TableColumn<GameResult, String> timeCol;

    public TableColumn<GameResult, String > playersCol;

    public TableColumn<GameResult, String> winnerCol;

    public TableColumn<GameResult, Integer> dominoesCol;


    public void initialize()
    {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        playersCol.setCellValueFactory(new PropertyValueFactory<>("players"));
        winnerCol.setCellValueFactory(new PropertyValueFactory<>("winner"));
        dominoesCol.setCellValueFactory(new PropertyValueFactory<>("dominoes"));

        ConnectionProperties cp = new ConnectionProperties();

        ObservableList<GameResult> results = FXCollections.observableArrayList();
        try {
            results.addAll(cp.selectRecord());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        GamesTable.setItems(results);
    }

    public void handleRestartGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
