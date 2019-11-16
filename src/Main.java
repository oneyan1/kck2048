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
import logic.*;

import java.util.Random;

import static logic.Constant.*;
import static logic.Direction.*;

public class Main {

    private static int score = 0; //lączna liczba punków
    private static boolean endGame = false; // wskaznika na koniec gry
    private static Field gameField; // pole gry
    private static Direction direction; // wskazniak przycisku
    private static Graphic graphic;

    private static void initFields(){
        gameField = new Field();
    }
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
            if(gameField.getStateCell(randomX, randomY) == 0){
                gameField.setStateCell(randomX,randomY,state);
                placed = true;
            }else{
                if(currentX+1 < COUNT_CELL_X) currentX++;
                else{
                    currentX = 0;
                    if(currentY+1 < COUNT_CELL_Y) currentY++;
                    else currentY = 0;
                }
                if(currentX == randomX && currentY == randomY){
                    System.err.println("Failed create new cell");
                    System.exit(-1);
                }
            }

        }
        score += state;

    }

    private static void input(){

    }

    private static void logic(){
        if(direction!=WAITING){
            if(shift(direction)) generateNewCell();
            direction = WAITING;
        }
    }

    private static boolean shift(Direction direction){
        boolean shift = false;
        switch(direction){
            case UP: break;
            case DOWN:break;
            case LEFT:break;
            case RIGHT:break;
            default:break;
        }
        return true;
    }

    public static void main(String[] args) {
        initFields(); // tworzenie nowego pola
        createInitialCell(); // tworzenie nowych punktów pola

        while(!endGame){
            //input();
            //logic();
            graphic.draw(gameField);
        }


    }

}