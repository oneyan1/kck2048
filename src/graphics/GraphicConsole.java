package graphics;


import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.EmptySpace;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.gui.layout.VerticalLayout;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import javafx.beans.binding.BooleanExpression;
import logic.Direction;
import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import static java.lang.Thread.*;


public class GraphicConsole implements Graphic {

    private final GUIScreen guiScreen = TerminalFacade.createGUIScreen();
    private Direction keyPressed;

    @Override
    public void draw(Field field){

//        final Window window = new Window("2048");
//        window.setWindowSizeOverride(new TerminalSize(25,15));
//        window.setSoloWindow(true);
//
//
//        Table table = new Table(5);
//
//        Component[] row1 = new Component[5];
//        row1[0] = new Label(field.toStringCell(0,0));
//        row1[1] = new Label(field.toStringCell(0,1));
//        row1[2] = new Label(field.toStringCell(0,2));
//        row1[3] = new Label(field.toStringCell(0,3));
//        row1[4] = new Label(field.toStringCell(0,3));
//
//        Component[] row2 = new Component[5];
//        row2[0] = new Label(field.toStringCell(1,0));
//        row2[1] = new Label(field.toStringCell(1,1));
//        row2[2] = new Label(field.toStringCell(1,2));
//        row2[3] = new Label(field.toStringCell(1,3));
//        row2[4] = new Label(field.toStringCell(1,3));
//
//        Component[] row3 = new Component[5];
//        row3[0] = new Label(field.toStringCell(2,0));
//        row3[1] = new Label(field.toStringCell(2,1));
//        row3[2] = new Label(field.toStringCell(2,2));
//        row3[3] = new Label(field.toStringCell(2,3));
//        row3[4] = new Label(field.toStringCell(2,3));
//
//        Component[] row4 = new Component[5];
//        row4[0] = new Label(field.toStringCell(3,0));
//        row4[1] = new Label(field.toStringCell(3,1));
//        row4[2] = new Label(field.toStringCell(3,2));
//        row4[3] = new Label(field.toStringCell(3,3));
//        row4[4] = new Label(field.toStringCell(3,3));
//
//        Component[] row5 = new Component[5];
//        row5[0] = new Label(field.toStringCell(4,0));
//        row5[1] = new Label(field.toStringCell(4,1));
//        row5[2] = new Label(field.toStringCell(4,2));
//        row5[3] = new Label(field.toStringCell(4,3));
//        row5[4] = new Label(field.toStringCell(4,3));
//
//        table.addRow(row1);
//        table.addRow(row2);
//        table.addRow(row3);
//        table.addRow(row4);
//        table.addRow(row5);
//        window.addComponent(table, LinearLayout.GROWS_HORIZONTALLY);


//        guiScreen.showWindow(window, GUIScreen.Position.CENTER);
        keyPressed = Direction.WAITING;
        guiScreen.getScreen().startScreen();
        guiScreen.getScreen().putString(35, 10 , field.toStringCell(0,0), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(35, 12 , field.toStringCell(0,1), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(35, 14 , field.toStringCell(0,2), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(35, 16 , field.toStringCell(0,3), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(35, 18 , field.toStringCell(0,4), Terminal.Color.WHITE, Terminal.Color.BLACK);

        guiScreen.getScreen().putString(40, 10 , field.toStringCell(1,0), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(40, 12 , field.toStringCell(1,1), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(40, 14 , field.toStringCell(1,2), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(40, 16 , field.toStringCell(1,3), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(40, 18 , field.toStringCell(1,4), Terminal.Color.WHITE, Terminal.Color.BLACK);

        guiScreen.getScreen().putString(45, 10 , field.toStringCell(2,0), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(45, 12 , field.toStringCell(2,1), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(45, 14 , field.toStringCell(2,2), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(45, 16 , field.toStringCell(2,3), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(45, 18 , field.toStringCell(2,4), Terminal.Color.WHITE, Terminal.Color.BLACK);

        guiScreen.getScreen().putString(50, 10 , field.toStringCell(3,0), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(50, 12 , field.toStringCell(3,1), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(50, 14 , field.toStringCell(3,2), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(50, 16 , field.toStringCell(3,3), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(50, 18 , field.toStringCell(3,4), Terminal.Color.WHITE, Terminal.Color.BLACK);

        guiScreen.getScreen().putString(55, 10 , field.toStringCell(4,0), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(55, 12 , field.toStringCell(4,1), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(55, 14 , field.toStringCell(4,2), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(55, 16 , field.toStringCell(4,3), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().putString(55, 18 , field.toStringCell(4,4), Terminal.Color.WHITE, Terminal.Color.BLACK);
        guiScreen.getScreen().refresh();
        boolean keepRunning = true;
        while(keepRunning){
            Key key = guiScreen.getScreen().readInput();
            while(key == null){
                key = guiScreen.getScreen().readInput();
            }

            System.out.println("Key pressed" + key.getKind().toString());

            switch(key.getKind()){
                case ArrowUp:
                    keyPressed = Direction.UP;
                    break;
                case ArrowDown:
                    keyPressed = Direction.DOWN;
                    break;
                case ArrowLeft:
                    keyPressed = Direction.LEFT;
                    break;
                case ArrowRight:
                    keyPressed = Direction.RIGHT;
                    break;
                case Escape:
                    guiScreen.getScreen().stopScreen();
                    System.exit(0);
                    break;
            }
        }
//
//        Panel panelHolder = new Panel("Menu", Orientation.VERTICAL);
//        window.addComponent(panelHolder);
//        window.addComponent(new EmptySpace());

//        final Window gameWindow = new Window("Nowa gra");
//
//        Button newGameBtn = new Button("Nowa gra", new Action() {
//            @Override
//            public void doAction() {
//
//
//            }
//        });
//        Button maxScoreBtn = new Button("Record");
//        Button exitBtn = new Button("Koniec gry", new Action() {
//            @Override
//            public void doAction() {
//                window.close();
//            }
//        });
//        newGameBtn.setAlignment(Alignment.CENTER);
//        maxScoreBtn.setAlignment(Alignment.CENTER);
//        exitBtn.setAlignment(Alignment.CENTER);
//        panelHolder.addComponent(newGameBtn, LinearLayout.GROWS_HORIZONTALLY);
//        panelHolder.addComponent(maxScoreBtn, LinearLayout.GROWS_HORIZONTALLY);
//        panelHolder.addComponent(exitBtn, LinearLayout.GROWS_HORIZONTALLY);



        //guiScreen.getScreen().stopScreen();




    }

    public Direction getKeyPressed() {
        return keyPressed;
    }

    @Override
    public void destroy() {

    }


}
