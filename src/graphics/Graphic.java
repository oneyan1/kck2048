package graphics;

import logic.Field;

import java.io.IOException;

public interface Graphic {
    void draw(Field field); // tworzenie pola gry
    void destroy(); //usuwanie pola

}
