package logic;

import static logic.Constant.*;

public class Field {
    private int[][] gameField;

    public Field(){
        gameField = new int[COUNT_CELL_X][COUNT_CELL_Y];

        for(int i = 0; i< gameField.length; i++){
            for(int j = 0; j< gameField[i].length; j++){
                gameField[i][j] = 0;
            }
        }
    }

    public int getStateCell(int i, int j) {
        return gameField[i][j];
    }

    public void setStateCell(int i, int j, int state) {
        gameField[i][j] = state;
    }


    public String toStringCell(int i, int j) {
        return String.valueOf(gameField[i][j]);
    }
}
