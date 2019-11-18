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
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import logic.Field;

import java.awt.*;



public class GraphicConsole implements Graphic {
    @Override
    public void draw(Field field) {
        final GUIScreen guiScreen = TerminalFacade.createGUIScreen();
        final Window window = new Window("2048");
        window.setWindowSizeOverride(new TerminalSize(25,15));
        window.setSoloWindow(true);

        Table table = new Table(5);

        Component[] row1 = new Component[5];
        row1[0] = new Label(field.toStringCell(0,0));
        row1[1] = new Label(field.toStringCell(0,1));
        row1[2] = new Label(field.toStringCell(0,2));
        row1[3] = new Label(field.toStringCell(0,3));
        row1[4] = new Label(field.toStringCell(0,3));

        Component[] row2 = new Component[5];
        row2[0] = new Label(field.toStringCell(1,0));
        row2[1] = new Label(field.toStringCell(1,1));
        row2[2] = new Label(field.toStringCell(1,2));
        row2[3] = new Label(field.toStringCell(1,3));
        row2[4] = new Label(field.toStringCell(1,3));

        Component[] row3 = new Component[5];
        row3[0] = new Label(field.toStringCell(2,0));
        row3[1] = new Label(field.toStringCell(2,1));
        row3[2] = new Label(field.toStringCell(2,2));
        row3[3] = new Label(field.toStringCell(2,3));
        row3[4] = new Label(field.toStringCell(2,3));

        Component[] row4 = new Component[5];
        row4[0] = new Label(field.toStringCell(3,0));
        row4[1] = new Label(field.toStringCell(3,1));
        row4[2] = new Label(field.toStringCell(3,2));
        row4[3] = new Label(field.toStringCell(3,3));
        row4[4] = new Label(field.toStringCell(3,3));

        Component[] row5 = new Component[5];
        row5[0] = new Label(field.toStringCell(4,0));
        row5[1] = new Label(field.toStringCell(4,1));
        row5[2] = new Label(field.toStringCell(4,2));
        row5[3] = new Label(field.toStringCell(4,3));
        row5[4] = new Label(field.toStringCell(4,3));

        table.addRow(row1);
        table.addRow(row2);
        table.addRow(row3);
        table.addRow(row4);
        table.addRow(row5);
        window.addComponent(table, LinearLayout.GROWS_HORIZONTALLY);

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

        guiScreen.getScreen().startScreen();
        guiScreen.showWindow(window, GUIScreen.Position.CENTER);
        guiScreen.getScreen().stopScreen();
    }

    @Override
    public void destroy() {

    }


}
