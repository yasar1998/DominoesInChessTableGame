package boardgame.dbconnector;

import boardgame.gui.GameResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProperties {
    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_gamescore?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String INSERT_QUERY = "INSERT INTO gamescore (time, players, winner, dominoes) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM gamescore order by time DESC limit 5";

    public void insertRecord(String t, String ps, String w, int ds) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY))
        {
            preparedStatement.setString(1, t);
            preparedStatement.setString(2, ps);
            preparedStatement.setString(3, w);
            preparedStatement.setInt(4, ds);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }

    }

    public List<GameResult> selectRecord() throws SQLException {
        List<GameResult> records = new ArrayList<>();

        ResultSet rst = null;
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {


            System.out.println(preparedStatement);
            rst = preparedStatement.executeQuery();

            GameResult gr = null;
            while (rst.next()) {
                records.add(new GameResult(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
            }

            return records;
        }

    }

}
