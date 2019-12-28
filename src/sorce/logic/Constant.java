package sorce.logic;

/**
 * Zawiera konstaty niezbędne do tworzenia gry
 */
public class Constant {
    /**
     *  Rozmiar pola po X
     */
    public static final int COUNT_CELL_X = 5;
    /**
     * Rozmiar pola po Y
     */
    public static final int COUNT_CELL_Y = 5;
    /**
     * Ilość zainicjowanych komórek
     */
    public static final int COUNT_INITAL_CELL = 2;

    public static final int CELL_SIZE = 64;
    public static final int SCREEN_WIDTH = COUNT_CELL_X * CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELL_Y * CELL_SIZE;
    public static final String SCREEN_NAME = "2048";
}
