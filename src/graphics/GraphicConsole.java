package graphics;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.terminal.TerminalSize;
import logic.Field;

import javax.swing.*;

public class GraphicConsole implements Graphic {
    @Override
    public void draw(Field field) {
        final GUIScreen guiScreen = TerminalFacade.createGUIScreen();
        final Window window = new Window("2048");
        window.setWindowSizeOverride(new TerminalSize(50,25));
        window.setSoloWindow(true);
    }

    @Override
    public void destroy() {

    }
}
