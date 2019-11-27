package logic;

import static logic.Constant.*;

/***
 * Klasa reprezantująca pole gry
 */
public class Field {
    /***
     * Tablica dwuwymiarowa o wymiarach pola gry
     */
    private int[][] gameField;

    /***
     * Konstruktor uzupełnia wszystkie komurki tablicy na 0
     */
    public Field(){
        gameField = new int[COUNT_CELL_X][COUNT_CELL_Y];

        for(int i = 0; i< gameField.length; i++){
            for(int j = 0; j< gameField[i].length; j++){
                gameField[i][j] = 0;
            }
        }
    }


    /***
     * @param i numer columny
     * @param j numer wiersza
     * @return Zwraca wartośc komurki o podanych współżędnych
     */
    public int getStateCell(int i, int j) {
        return gameField[i][j];
    }

    // ustawia wartośc komurki state o podanych wspołżędnuch i,j

    /***
     * @param i numer columny
     * @param j numer wiersza
     * @param state wartość ktorą trzeba wpisac do komurki o wspołżędnych i,j
     */
    public void setStateCell(int i, int j, int state) {
        gameField[i][j] = state;
    }


    /***
     * @param i numer kolumny
     * @return zwraca kolumne o podanych numerze
     */
    public int[] getColumn(int i){
        return gameField[i];
    }

    // przepisuje columne i wartośc nowej kolumny col

    /***
     * Przepisuje kolumne o podanym numerze
     * @param i numer columny
     * @param col nowa kolumna
     */
    public void setColumn(int i, int[] col){
        gameField[i] = col;
    }


    /***
     * @param i numer wiersza
     * @return zwraca wiersz po wskazanycm numererm
     */
    public int[] getRow(int i){
        int[] ret = new int[COUNT_CELL_X];
        for (int j = 0; j < COUNT_CELL_X; j++) {
            ret[j] = gameField[j][i];
        }
        return ret;
    }


    /**
     * Prepusuje wartość wiersza na nowy wartości
     * @param i numer wiersza
     * @param newRow nowy wiersz
     */
    public void setRow(int i, int[] newRow){
        for (int j = 0; j < COUNT_CELL_X ; j++) {
            gameField[j][i] = newRow[j];
        }
    }


    /**
     *  Zwraca wartość komurki tablicy z typem String
     * @param i numer kolumny
     * @param j numer wiersza
     * @return wartość komurki z typem String
     */
    public String toStringCell(int i, int j) {
        return String.valueOf(gameField[i][j]);
    }
}
