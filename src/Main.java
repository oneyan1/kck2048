
import graphics.Graphic;
import graphics.GraphicConsole;
import keyboard.KeyboardHundle;
import keyboard.KeyboardHundleConsole;
import logic.*;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static logic.Constant.*;
import static logic.Direction.*;

public class Main {


    /***
     * Obiekt tej klasy jest wynikiem działania metody shiftRow(),
     *  zawiera zmieniony wiersz i wartość boolową wskazującą czy rowny on originalnemu werszowi
     */
    private static class ShiftRowResult {
        boolean moving;
        int[] shiftedRow;
    }

    /***
     * lączna liczba punków
     */
    private static int score;
    /***
     * wskaznika na koniec gry
     */
    private static boolean endGame;
    /***
     * Obiekt pola gry
     */
    private static Field gameField;
    /***
     * Obiekt wskazujący na kierunek przycisku
     */
    private static Direction direction;
    /***
     * Obiekt interfajsu tekstowego
     */
    private static GraphicConsole graphic;
    /***
     * Obiekt klawiatury(w tej implementacji nie wykożystany)
     */
    private static KeyboardHundle keyboard;

    /**
     * Tablica rekordów z poprzednich gier
     */
    private static int scoreRecords[];

    /***
     * Inicjalizuje pola niezbedne dla starta gry
     */
    private static void initFields() {
        score = 0;
        scoreRecords = new int[3];
        endGame = false;
        graphic = new GraphicConsole();
        //keyboard = new KeyboardHundleConsole();
        gameField = new Field();
        scoreRecords();

    }

    /***
     *Tworzy nowe komórki z na starcie gry i przy przesuwaniu columny albo wiersza
     */
    private static void createInitialCell() {
        for (int i = 0; i < COUNT_INITAL_CELL; i++) {
            generateNewCell();
        }
    }


    /***
     * Generowanie nowej liczby wraz ze współrzędnymi do jej umieszczenia na pole gry
     */
    private static void generateNewCell() {
        int state = 2;  // pózniej dodac szanse wylosowania 4
        int randomX = new Random().nextInt(COUNT_CELL_X);
        int randomY = new Random().nextInt(COUNT_CELL_Y);
        boolean placed = false;
        int currentX = randomX, currentY = randomY;
        while (!placed) {
            if (gameField.getStateCell(currentX, currentY) == 0) {
                gameField.setStateCell(currentX, currentY, state);
                placed = true;
            } else {
                if (currentX + 1 < COUNT_CELL_X) {
                    currentX++;
                } else {
                    currentX = 0;
                    if (currentY + 1 < COUNT_CELL_Y) {
                        currentY++;
                    } else {
                        currentY = 0;
                    }
                }
                if ((currentX == randomX) && (currentY == randomY)) {
                    System.err.println("Failed create new cell");
                    System.exit(-1);
                }
            }

        }
        score += state;

    }

    /***
     * Ustawia wartość przyciska ktory był wciśnięty przez użytkownika
     */
    private static void input() {
        direction = graphic.getKeyPressed();
    }

    /***
     * Wywoluje generowanie nowego punktu jezeli był wciśnienty przycisk
     */
    private static void logic() {
        if (direction != WAITING) {
            if (shift(direction)) generateNewCell();
            direction = WAITING;
        }
    }

    /***
     * Przesuwa i zlicza komurki:
     * 1. Jezeli w werszu/kolumnie są 0, usuwa ich;
     * 2. Jezeli dwe komurki mają taką samą wartość, to sumuje ich
     * 3. Jezeli komurka nowoutrwożona to nie może być sumowane
     * 4. Sprawdzanie liczb idzie od 0-wego indeksu k ostatniemu
     *
     * @param oldRow wiersz/kolumna w przedsawenu tablicy jednowymiarowej komurki której trzeba przesunuć
     * @return true w przypadku gdy przesuwanie zostało wykonane
     */
    private static ShiftRowResult shiftRow(int[] oldRow) {
        ShiftRowResult returnArray = new ShiftRowResult();

        int[] withoutZero = new int[oldRow.length];
        {
            int count = 0;
            for (int i = 0; i < oldRow.length; i++) {
                if (oldRow[i] != 0) {
                    if (count != i) {
                        returnArray.moving = true;
                    }
                    withoutZero[count] = oldRow[i];
                    count++;
                }
            }
        }
        returnArray.shiftedRow = new int[withoutZero.length];
        {
            int count = 0;
            {
                int i = 0;
                while (i < withoutZero.length) {
                    if (i + 1 < withoutZero.length && withoutZero[i] == withoutZero[i + 1] && withoutZero[i] != 0) {
                        returnArray.moving = true;
                        returnArray.shiftedRow[count] = withoutZero[i] * 2;
                        i++;
                    } else {
                        returnArray.shiftedRow[count] = withoutZero[i];
                    }
                    count++;
                    i++;
                }
            }
            for (int i = count; i < returnArray.shiftedRow.length; i++) {
                returnArray.shiftedRow[i] = 0;
            }
        }
        return returnArray;
    }

    /***
     * Przesuwa wszystkie komurki we wskazanym kierunku, wywyoluja shiftRow() dla każdego
     * wiersza/kolumny w zalezności od wskazanego kierunku
     *
     * @param direction kirunek w ktorem trzeba przesunuć wiersz/kolumne
     * @return zwraza true w przypadku gdy przesuwanie się udało
     */
    private static boolean shift(Direction direction) {
        boolean shift = false;
        switch (direction) {
            case UP:
            case DOWN:
                for (int i = 0; i < COUNT_CELL_X; i++) {
                    int[] col = gameField.getColumn(i);

                    if (direction == UP) {
                        int[] tmp = new int[col.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = col[tmp.length - j - 1];
                        }
                        col = tmp;
                    }
                    ShiftRowResult result = shiftRow(col);

                    if (direction == UP) {
                        int[] tmp = new int[result.shiftedRow.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = result.shiftedRow[tmp.length - j - 1];
                        }
                        result.shiftedRow = tmp;
                    }
                    gameField.setColumn(i, result.shiftedRow);
                    shift = shift || result.moving;
                }
                break;

            case LEFT:
            case RIGHT:
                for (int i = 0; i < COUNT_CELL_Y; i++) {
                    int[] row = gameField.getRow(i);

                    if (direction == RIGHT) {
                        int[] tmp = new int[row.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = row[tmp.length - j - 1];
                        }
                        row = tmp;
                    }
                    ShiftRowResult result = shiftRow(row);

                    if (direction == RIGHT) {
                        int[] tmp = new int[result.shiftedRow.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = result.shiftedRow[tmp.length - j - 1];
                        }
                        result.shiftedRow = tmp;
                    }

                    gameField.setRow(i, result.shiftedRow);

                    shift = shift || result.moving;
                }
                break;

            default:
                System.err.println("Wrong parameter on Direction");
                System.exit(-2);
                break;
        }
        return shift;
    }

    /***
     * Odczytanie tablicy recordów z pliku
     */
    private static void scoreRecords(){
        File file = new File("score.txt");
        Scanner scan;
        try {
            scan = new Scanner(file);
            int i = 0;
            while(scan.hasNextLine()){
                scoreRecords[i] = Integer.parseInt(scan.nextLine());
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zapisywanie nowego recordu do pliku
     */
    private static void writeNewScoreRecords(){
        try(final FileWriter writer = new FileWriter("score.txt", false)) {
            for (int i = scoreRecords.length; i > 0; i--) {
                System.out.println(scoreRecords[i]);
                if (scoreRecords[i] <= score) {
                    scoreRecords[i] = score;
                }
            }
            for (int i = 0; i < scoreRecords.length; i++) {
                writer.write(Integer.toString(scoreRecords[i]));
                writer.write(System.lineSeparator());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        initFields(); // tworzenie nowego pola
        createInitialCell(); // tworzenie nowych punktów pola
        graphic.mainMenu(gameField, score);
        endGame = graphic.getCloseGame();
        while (!endGame) {
            graphic.draw(gameField, score);
            input();
            logic();
            System.out.println("Score: " + score);
            System.out.println(Arrays.toString(scoreRecords));
            endGame = graphic.getCloseGame();
        }
        writeNewScoreRecords();
    }
}