package keyboard;

import com.googlecode.lanterna.input.Key;

/**
 * interface obslugujący klawiaturą
 */
public interface KeyboardHundle {
    void update(Key key); //czytanie posczegolnych wcisnienc klawisza
}
