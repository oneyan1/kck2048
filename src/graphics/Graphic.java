package graphics;

import logic.Field;

import java.io.IOException;

/**
 * Interface dla tworzenia pola gry
 */
public interface Graphic {
    void draw(Field field, int score); // tworzenie pola gry
    void destroy(); //usuwanie pola

}
