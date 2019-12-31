package sorce.graphics;

import sorce.logic.Field;

/**
 * Interface dla tworzenia pola gry
 */
public interface Graphic {
    void draw(Field field, int score, int newGame); // tworzenie pola gry
    void destroy(); //usuwanie pola
    boolean isCloseRequested();
}
