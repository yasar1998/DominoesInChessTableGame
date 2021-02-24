package boardgame.gui;

public class GameResult {
    public String start;
    public String players;
    public String winner;
    public int dominoes;

    public GameResult(String start, String players, String winner, int dominoes) {
        this.start = start;
        this.players = players;
        this.winner = winner;
        this.dominoes = dominoes;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getDominoes() {
        return dominoes;
    }

    public void setDominoes(int dominoes) {
        this.dominoes = dominoes;
    }
}
