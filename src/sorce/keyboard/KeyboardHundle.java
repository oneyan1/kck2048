package sorce.keyboard;


import sorce.logic.Direction;

/**
 * interface obslugujący klawiaturą
 */
public interface KeyboardHundle {
    void update(); //czytanie posczegolnych wcisnienc klawisza
    Direction getLastKeyPressed();
    boolean getWasEscPressed();
    int getNewGame();
}
