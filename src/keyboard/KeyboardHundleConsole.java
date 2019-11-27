package keyboard;

import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.Key;
import logic.Direction;

public class KeyboardHundleConsole implements KeyboardHundle {

    private Direction keyPressed;

    @Override
    public void update(Key key) {
        keyPressed = Direction.WAITING;

    }
}
