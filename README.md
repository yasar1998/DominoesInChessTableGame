# "Dominoes in Chess Table" Game
## Good example project for Java MySql database connectivity where GUI is provided with JavaFX

## Game Description
As it is obviously seen from the name, the game consists of two elements: dominoes and chessboard. This game is two-player game where first player must place
domino on the board horizontally and second player must do it vertically. Game is finished when any one of the players can not place domino on the board
anymore and automatically another player wins the match.

## User Guide
1. The game starts with the page which requires players' name to be entered to start the game. Failing to write the names will not start the game and warning
text will appear

![Screenshot from 2021-02-24 00-27-57](https://user-images.githubusercontent.com/31879611/118412550-f36a9780-b69a-11eb-92f0-abb6649a764f.png)


2. When the game started, the start time of game, names of players appear on screen.
   During the game, the name of next turn is labeled, number of dominoes are counted and shown on the screen.
   At the end of the game, winner name appear on the screen.
   For simplicity, game is provided with reset button to restart game and finish button to end it
   
![Screenshot from 2021-05-16 22-52-33](https://user-images.githubusercontent.com/31879611/118412395-f4e79000-b699-11eb-9ae7-b7080c2c1c14.png)
   
3. Last page is about details of last five games which has been recently played. Restart button has been provided there to start new game directly.

![Screenshot from 2021-05-16 23-02-50](https://user-images.githubusercontent.com/31879611/118412577-109f6600-b69b-11eb-81ad-38280b134e71.png)


## Some Technical Description
Before running the project, javafx and database configuration are needed.
- Database must be created in MySql server and the corresponding table must be added (If they don't exist)
- JavaFx jar files and database connector jar file must be added to global library for the project (IntelliJ IDEA)

1. After clicking finish button, all necessary information about current game is stored in MySql Database and last game page is being loaded
2. When last game page starts, it is initialized with data which come from MySql Database where last five game details are shown
