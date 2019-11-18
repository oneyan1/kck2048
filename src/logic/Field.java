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

    //zwraca wartość komurki o podanych wszpołżędnuch
    public int getStateCell(int i, int j) {
        return gameField[i][j];
    }

    // ustawia wartośc komurki state o podanych wspołżędnuch i,j
    public void setStateCell(int i, int j, int state) {
        gameField[i][j] = state;
    }

    //zwraca kolumne i
    public int[] getColumn(int i){
        return gameField[i];
    }

    // przepisuje columne i wartośc nowej kolumny col
    public void setColumn(int i, int[] col){
        gameField[i] = col;
    }

    //zwrace wiersz-tablice pod numerem i
    public int[] getRow(int i){
        int[] ret = new int[COUNT_CELL_X];
        for (int j = 0; j < COUNT_CELL_X; j++) {
            ret[j] = gameField[j][i];
        }
        return ret;
    }

    // przepisuje wiersz pod numerem i
    public void setRow(int i, int[] newRow){
        for (int j = 0; j < COUNT_CELL_X ; j++) {
            gameField[j][i] = newRow[j];
        }
    }

    // zwraca zawartość komurki tablicy z typem string // sprawdzic czy to jest potrebne!!!!
    public String toStringCell(int i, int j) {
        return String.valueOf(gameField[i][j]);
    }
}
