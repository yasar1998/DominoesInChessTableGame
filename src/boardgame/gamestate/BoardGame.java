package boardgame.gamestate;

public class BoardGame {
    public int[][] cellsState;

    public int r;

    public int c;

    private int count = 0;

    private int dominoes = 0;

    public final int[][] INITIAL = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public final int[][] TESTCASE =  {
            {1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 0, 1},
            {0, 1, 0, 1, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 1, 0}
    };

    public BoardGame()
    {
        cellsState = INITIAL;
    }

    public BoardGame(int[][] testInitial)
    {
        cellsState = testInitial;
    }

    public void myVersionInitial()
    {
        cellsState = TESTCASE;
    }

    private boolean canClick(int i, int j)
    {
        if(((i < 0 || i >= 8) || (j < 0 || j >= 8)))
        {
            return false;
        }

        if(cellsState[i][j] == 1)
        {
            return false;
        }

        return true;
    }

    public int numberOfDominoes() {
        return dominoes;
    }

    public void clicked(int i, int j)
    {
        if(canClick(i, j) && (count%4==0))
        {
            if((j == 0 && cellsState[i][j+1] == 0) || (j == 7 && cellsState[i][j-1] == 0) || (j !=0 && j != 7 && (cellsState[i][j-1] == 0 || cellsState[i][j+1] == 0)))
            {
                cellsState[i][j] = 1;
                r = i;
                c = j;
                count++;
            }
        }
        if(canClick(i, j) && (count%4 == 1))
        {
            if((j == c + 1 || j == c - 1) && r == i )
            {
                cellsState[i][j] = 1;
                count++;
                dominoes++;
            }
        }
        if(canClick(i, j) && (count%4 == 2))
        {
            if((i == 0 && cellsState[i + 1][j] == 0) || (i == 7 && cellsState[i - 1][j] == 0) || (i != 0 && i != 7 && (cellsState[i - 1][j] == 0 || cellsState[i + 1][j] == 0)))
            {
                cellsState[i][j] = 1;
                r = i;
                c = j;
                count++;
            }
        }
        if(canClick(i, j) && (count%4 == 3))
        {
            if((i == r + 1 || i == r - 1) && j == c)
            {
                cellsState[i][j] = 1;
                count++;
                dominoes++;
            }
        }
    }

    public boolean isFinishedHorizontal() {
        if (this.count % 4 == 0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (cellsState[i][j] == 0 && cellsState[i][j - 1] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        else
            return false;
    }

    public boolean isFinishedVertical() {
        if (this.count % 4 == 2) {
            for (int i = 1; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (cellsState[i - 1][j] == 0 && cellsState[i][j] == 0) {
                        return false;
                    }
                }
            }

            return true;
        }
        else
            return false;
    }


    public void displayBoard()
    {
        for(int i = 0; i < cellsState.length; i++)
        {
            for(int j = 0; j < cellsState[0].length; j++)
            {
                System.out.print(cellsState[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
