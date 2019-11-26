package graphics;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Component.Alignment;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.EmptySpace;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.gui.layout.VerticalLayout;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import logic.Direction;
import logic.Field;


public class GraphicConsole implements Graphic {

    private final GUIScreen guiScreen = TerminalFacade.createGUIScreen();
    private Direction keyPressed;
    private boolean closeGame = false;

    public boolean getCloseGame(){
        return closeGame;
    }
    @Override
    public void draw(Field field){

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
                    mainMenu(field);
                    System.exit(0);
                    break;
            }
        }

    }

    public Direction getKeyPressed() {
        return keyPressed;
    }

    public void mainMenu(Field field){
        final Window window = new Window("2048");
        window.setWindowSizeOverride(new TerminalSize(20,10));
        window.setSoloWindow(true);
        Panel menuPanel = new Panel("Menu", Orientation.VERTICAL);
        menuPanel.setLayoutManager(new VerticalLayout());
        Button newGameBtn = new Button("New game", new Action() {
            @Override
            public void doAction() {
                window.close();
                guiScreen.getScreen().stopScreen();
                draw(field);
            }
        });
        newGameBtn.setAlignment(Alignment.CENTER);
        menuPanel.addComponent(newGameBtn, LinearLayout.GROWS_HORIZONTALLY);


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
