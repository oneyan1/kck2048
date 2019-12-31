package sorce.keyboard;

import org.lwjgl.input.Keyboard;
import sorce.logic.Direction;

public class KeyboardHundleDesktop implements KeyboardHundle{

    private boolean wasEscPressed;
    private int newGame;
    private Direction lastKeyPressed;

     private void resetValue(){
        wasEscPressed = false;
        lastKeyPressed = Direction.WAITING;
     }

    @Override
    public void update() {
        resetValue();
        while (Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        if(newGame == 0){
                            wasEscPressed = true;
                        }else newGame = 0;
                        break;
                    case Keyboard.KEY_UP:
                        lastKeyPressed = Direction.UP;
                        break;
                    case Keyboard.KEY_DOWN:
                        lastKeyPressed = Direction.DOWN;
                        break;
                    case Keyboard.KEY_LEFT:
                        lastKeyPressed = Direction.LEFT;
                        break;
                    case Keyboard.KEY_RIGHT:
                        lastKeyPressed = Direction.RIGHT;
                        break;
                    case Keyboard.KEY_RETURN:
                        newGame = 1;
                        break;
                    case Keyboard.KEY_S:
                        newGame = 2;
                        break;
                    default:
                }
            }
        }
    }

    public Direction getLastKeyPressed(){
         return lastKeyPressed;
    }

    public boolean getWasEscPressed(){
         return wasEscPressed;
    }

    public int getNewGame(){ return newGame; }
}
