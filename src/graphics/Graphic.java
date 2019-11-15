package graphics;

import logic.Field;

public interface Graphic {
    void draw(Field field); // tworzenie pola gry
    void destroy(); //usuwanie pola

}
