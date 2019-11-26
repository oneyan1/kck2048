//import java.nio.charset.Charset;
//
//import com.googlecode.lanterna.TerminalFacade;
//import com.googlecode.lanterna.gui.*;
//import com.googlecode.lanterna.gui.Component.Alignment;
//import com.googlecode.lanterna.gui.component.Button;
//import com.googlecode.lanterna.gui.component.EmptySpace;
//import com.googlecode.lanterna.gui.component.Label;
//import com.googlecode.lanterna.gui.component.Panel;
//import com.googlecode.lanterna.gui.component.Panel.Orientation;
//import com.googlecode.lanterna.gui.component.Table;
//import com.googlecode.lanterna.gui.layout.LinearLayout;
//import com.googlecode.lanterna.gui.layout.VerticalLayout;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.screen.ScreenCharacterStyle;
//import com.googlecode.lanterna.terminal.Terminal;
//import com.googlecode.lanterna.terminal.TerminalSize;
import graphics.Graphic;
import graphics.GraphicConsole;
import keyboard.KeyboardHundle;
import keyboard.KeyboardHundleConsole;
import logic.*;

import java.util.Random;

import static logic.Constant.*;
import static logic.Direction.*;

public class Main {

    //wynik dzałanie metody shiftRow(), object zawiera zmieneny wiersz, i bool
    // wskazujący czy rowny on wartości originalnej
    private static class ShiftRowResult{
        boolean moving;
        int[] shiftedRow;
    }
    private static int score; //lączna liczba punków
    private static boolean endGame; // wskaznika na koniec gry
    private static Field gameField; // pole gry
    private static Direction direction; // wskazniak przycisku
    private static GraphicConsole graphic ; //tworzenie nowego interface tekstowego
    private static KeyboardHundle keyboard;

    private static void initFields(){
        score = 0;
        endGame = false;
        graphic = new GraphicConsole();
        //keyboard = new KeyboardHundleConsole();
        gameField = new Field();

    }
    //tworzy nowe komurki na starcie gry
    private static void createInitialCell(){
        for (int i = 0; i < COUNT_INITAL_CELL ; i++) {
            generateNewCell();
        }
    }

    /*generuje nowe liczby*/
    private static void generateNewCell(){
        int state = 2;  // pózniej dodac szanse wylosowania 4
        int randomX = new Random().nextInt(COUNT_CELL_X);
        int randomY = new Random().nextInt(COUNT_CELL_Y);
        boolean placed = false;
        //System.out.println(randomX+ " "+ randomY);
        int currentX = randomX, currentY = randomY;
        while(!placed){
            if(gameField.getStateCell(currentX, currentY) == 0){
                gameField.setStateCell(currentX,currentY,state);
                placed = true;
            }else{
                if(currentX+1 < COUNT_CELL_X) {
                    currentX++;
                }
                else{
                    currentX = 0;
                    if(currentY+1 < COUNT_CELL_Y){
                        currentY++;
                    }
                    else{
                        currentY = 0;
                    }
                }
                if((currentX == randomX) && (currentY == randomY)){
                    System.err.println("Failed create new cell");
                    System.exit(-1);
                }
            }

        }
        score += state;

    }

    private static void input(){
        //graphic.draw(gameField);
        direction = graphic.getKeyPressed();
    }

    private static void logic(){
        if(direction!=WAITING){
            if(shift(direction)) generateNewCell();
            direction = WAITING;
        }
    }

    private static ShiftRowResult shiftRow(int[] oldRow){
        ShiftRowResult returnArray = new ShiftRowResult();

        int[] withoutZero = new int[oldRow.length];
        {
            int count = 0;
            for (int i = 0; i < oldRow.length; i++) {
                if(oldRow[i] != 0){
                    if(count != i){
                        returnArray.moving = true;
                    }
                    withoutZero[count] = oldRow[i];
                    count++;
                }
            }
        }
        returnArray.shiftedRow = new int[withoutZero.length];
        {
            int count = 0;
            {
                int i = 0;
                while(i < withoutZero.length){
                    if(i+1 < withoutZero.length && withoutZero[i] == withoutZero[i+1] && withoutZero[i] != 0){
                        returnArray.moving = true;
                        returnArray.shiftedRow[count] = withoutZero[i]*2;
                        i++;
                    }else{
                        returnArray.shiftedRow[count] = withoutZero[i];
                    }
                    count++;
                    i++;
                }
            }
            for (int i = count; i < returnArray.shiftedRow.length ; i++) {
                returnArray.shiftedRow[i] = 0;
            }
        }
        return returnArray;
    }

    private static boolean shift(Direction direction){
        boolean shift = false;
        switch(direction){
            case UP:
            case DOWN:
                for (int i = 0; i < COUNT_CELL_X ; i++) {
                    int[] col = gameField.getColumn(i);

                    if(direction == UP){
                        int[] tmp = new int[col.length];
                        for (int j = 0; j < tmp.length ; j++) {
                            tmp[j] = col[tmp.length - j-1];
                        }
                        col = tmp;
                    }
                    ShiftRowResult result = shiftRow(col);

                    if(direction == UP){
                        int[] tmp = new int[result.shiftedRow.length];
                        for (int j = 0; j < tmp.length ; j++) {
                            tmp[j] = result.shiftedRow[tmp.length - j-1];
                        }
                        result.shiftedRow = tmp;
                    }
                    gameField.setColumn(i, result.shiftedRow);
                    shift = shift || result.moving;
                }
                break;

            case LEFT:
            case RIGHT:
                for (int i = 0; i < COUNT_CELL_Y; i++) {
                    int[] row = gameField.getRow(i);

                    if(direction == RIGHT){
                        int[] tmp = new int[row.length];
                        for (int j = 0; j < tmp.length ; j++) {
                            tmp[j] = row[tmp.length - j-1];
                        }
                        row = tmp;
                    }
                    ShiftRowResult result = shiftRow(row);

                    if(direction == RIGHT){
                        int[] tmp = new int[result.shiftedRow.length];
                        for (int j = 0; j < tmp.length ; j++) {
                            tmp[j] = result.shiftedRow[tmp.length - j-1];
                        }
                        result.shiftedRow = tmp;
                    }

                    gameField.setRow(i, result.shiftedRow);

                    shift = shift || result.moving;
                }
                break;

            default:
                System.err.println("Wrong parameter on Direction");
                System.exit(-2);
                break;
        }
        return shift ;
    }



    public static void main(String[] args) {
        initFields(); // tworzenie nowego pola
        createInitialCell(); // tworzenie nowych punktów pola
        graphic.mainMenu(gameField);
        endGame = graphic.getCloseGame();
        while(!endGame){
            graphic.draw(gameField);
            input();
            logic();
            System.out.println("Score: " + score);
        }

    }
}