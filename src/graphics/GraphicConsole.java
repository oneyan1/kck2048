package graphics;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.gui.layout.VerticalLayout;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import logic.Direction;
import logic.Field;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementacja interfejsa tekstowego
 */
public class GraphicConsole implements Graphic {

    /**
     * Tworzenia nowego okna terminala
     */
    private final GUIScreen guiScreen = TerminalFacade.createGUIScreen();
    /**
     * Objekt kierunku przyciska
     */
    private Direction keyPressed;
    /**
     * Wskaznik na zakończenie gry
     */
    private boolean closeGame = false;

    /**
     * @return zwraca wskaznik na zakończenia gry
     */
    public boolean getCloseGame(){
        return closeGame;
    }

    /**
     *  Tworzenie i rysowanie pola gry, ustawienie obekta keyPressed
     *  na kierunek przycisku
     * @param field obiekt pola gry
     */
    @Override
    public void draw(Field field, int score){

        guiScreen.getScreen().startScreen();
        //segment rysujący kwadrat pola
        guiScreen.getScreen().putString(33, 8, "_________________________", Terminal.Color.WHITE, Terminal.Color.BLACK);
        for (int i = 9; i < 20 ; i++) {
            guiScreen.getScreen().putString(32, i, "|", Terminal.Color.WHITE, Terminal.Color.BLACK);
        }
        for (int i = 9; i < 20; i++) {
            guiScreen.getScreen().putString(57, i, "|", Terminal.Color.WHITE, Terminal.Color.BLACK);
        }
        guiScreen.getScreen().putString(33, 19, "________________________", Terminal.Color.WHITE, Terminal.Color.BLACK);

        //segmanet wypisujący wartośći tablicy
        for(int x = 35, i = 0; x < 60 && i < 5; x+=5, i++){
                for(int y = 10, j = 0; y < 20 && j < 5; y+=2, j++){
                    guiScreen.getScreen().putString(x,y, "  ", Terminal.Color.WHITE, Terminal.Color.BLACK);
                    if(field.getStateCell(i,j) == 0){
                        guiScreen.getScreen().putString(x,y, "", Terminal.Color.WHITE, Terminal.Color.BLACK);
                    }
                    else{
                        guiScreen.getScreen().putString(x,y, field.toStringCell(i, j), Terminal.Color.WHITE, Terminal.Color.BLACK);
                    }
                }
        }
        guiScreen.getScreen().putString(10,5,"Score: " + Integer.toString(score), Terminal.Color.BLACK, Terminal.Color.WHITE);

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
                    keyPressed = Direction.DOWN;
                    keepRunning = false;
                    break;
                case ArrowDown:
                    keyPressed = Direction.UP;
                    keepRunning = false;
                    break;
                case ArrowLeft:
                    keyPressed = Direction.LEFT;
                    keepRunning = false;
                    break;
                case ArrowRight:
                    keyPressed = Direction.RIGHT;
                    keepRunning = false;
                    break;
                case Escape:
                    guiScreen.getScreen().stopScreen();
                    keepRunning = false;
                    mainMenu(field, score);
                    break;
            }
        }

    }

    /**
     * @return zwraca obiekt kierunku przycisku
     */
    public Direction getKeyPressed() {
        return keyPressed;
    }

    /**
     * Tworzy menu gry
     * @param field obuekt pola gry\
     * @param score liczba punktów zdobyta w trakcie gry
     */
    public void mainMenu(Field field, int score){
        final Window window = new Window("2048");
        window.setWindowSizeOverride(new TerminalSize(20,10));
        window.setSoloWindow(true);
        Panel menuPanel = new Panel("Menu", Orientation.VERTICAL);
        menuPanel.setLayoutManager(new VerticalLayout());
        Button newGameBtn = new Button("New game", new Action() {
            @Override
            public void doAction() {
                window.close();
                closeGame = false;
                guiScreen.getScreen().stopScreen();
                draw(field, score);
            }
        });
        newGameBtn.setAlignment(Alignment.CENTER);
        menuPanel.addComponent(newGameBtn, LinearLayout.GROWS_HORIZONTALLY);

        final Window scoreWindow = new Window("Score");
        Button scoreBtn = new Button("Score", new Action() {
            @Override
            public void doAction() {
                scoreWindow.setWindowSizeOverride(new TerminalSize(30,10));
                scoreWindow.setSoloWindow(true);
                File file = new File("score.txt");
                String scoreRecord[] = new String[3];
                Scanner scan;
                try {
                    scan = new Scanner(file);
                    int i = 0;
                    while(scan.hasNextLine()){
                        scoreRecord[i] = scan.nextLine();
                        i++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Panel scorePanel = new Panel("Score", Orientation.VERTICAL);
                scorePanel.setLayoutManager(new VerticalLayout());
                Table scoreTable = new Table(3);

                Component[] col1 = new Component[3];
                col1[0] = new Label(scoreRecord[0]);
                col1[1] = new Label(scoreRecord[1]);
                col1[2] = new Label(scoreRecord[2]);

                scoreTable.addRow(col1);
                scorePanel.addComponent(scoreTable);
                Button combackBtn = new Button("Back", new Action() {
                    @Override
                    public void doAction() {
                        scoreWindow.close();
                        window.close();
                        guiScreen.getScreen().stopScreen();
                        mainMenu(field, score);
                    }
                });
                scorePanel.addComponent(combackBtn);
                scoreWindow.addComponent(scorePanel);
                guiScreen.showWindow(scoreWindow, GUIScreen.Position.CENTER);
            }
        });
        scoreBtn.setAlignment(Alignment.CENTER);
        menuPanel.addComponent(scoreBtn, LinearLayout.GROWS_HORIZONTALLY);

        Button exitBtn = new Button("Exit", new Action() {
            @Override
            public void doAction() {
                closeGame = true;
                window.close();
                guiScreen.getScreen().stopScreen();
            }
        });
        exitBtn.setAlignment(Alignment.CENTER);
        menuPanel.addComponent(exitBtn, LinearLayout.GROWS_HORIZONTALLY);


        window.addComponent(menuPanel);
        window.addComponent(new EmptySpace());

        guiScreen.getScreen().startScreen();
        guiScreen.showWindow(window, GUIScreen.Position.CENTER);


    }

    @Override
    public void destroy() {

    }


}
