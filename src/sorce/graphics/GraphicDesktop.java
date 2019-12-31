package sorce.graphics;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import sorce.logic.Constant;
import sorce.logic.Field;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GraphicDesktop implements Graphic {
    private SpriteSystem sprite;
    private NumberSpriteSystem numSprite;
    private Texture texture;
    public GraphicDesktop(){
        initOpengl();
        sprite = new SpriteSystem();
        numSprite = new NumberSpriteSystem();
    }

    private void initOpengl(){
        try {
            Display.setDisplayMode(new DisplayMode(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
            Display.setTitle(Constant.SCREEN_NAME);
            Display.create();
        }catch (LWJGLException e){
            System.err.println("GraphicsModule failed.");
            e.printStackTrace();
            System.exit(-3);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constant.SCREEN_WIDTH, 0, Constant.SCREEN_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(1,1,1,1);
    }


    @Override
    public void draw(Field field, int score, int newGame) {
        glClear(GL_COLOR_BUFFER_BIT);
        if(newGame == 1){
            drawGameField(field);
        }
        else if(newGame == 0){
            drawMainMenu();
        }
        else if(newGame == 2){
            drawScoreMenu();
        }
        Display.update();
        Display.sync(60);
    }

    private void drawGameField(Field field){
        for(int i = 0; i < Constant.COUNT_CELL_X; i++){
            for(int j= 0; j < Constant.COUNT_CELL_Y; j++){
                drawCell(Constant.CELL_SIZE*i, Constant.CELL_SIZE*j, field.getStateCell(i,j));
            }
        }
    }

    private void drawCell(int x, int y, int state){
        sprite.getSpriteByNumber(state).getTexture().bind();

        glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(x,y+ Constant.CELL_SIZE);
            glTexCoord2f(1,0);
            glVertex2f(x+ Constant.CELL_SIZE,y+ Constant.CELL_SIZE);
            glTexCoord2f(1,1);
            glVertex2f(x+ Constant.CELL_SIZE, y);
            glTexCoord2f(0,1);
            glVertex2f(x, y);
        glEnd();
    }

    private void drawMainMenu(){
        Texture menuTexture = loadMenuTexture("full");

        menuTexture.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(0, -150);
            glTexCoord2f(0,0);
            glVertex2i(0, 320 );
            glTexCoord2f(1,0);
            glVertex2i(470, 320 );
            glTexCoord2f(1,1);
            glVertex2i(470, -150);
        glEnd();
    }

    private void drawScoreMenu(){
        Texture scoreTexture = loadMenuTexture("scorebg");

        scoreTexture.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0,1);
            glVertex2i(0, -150);
            glTexCoord2f(0,0);
            glVertex2i(0, 320 );
            glTexCoord2f(1,0);
            glVertex2i(470, 320 );
            glTexCoord2f(1,1);
            glVertex2i(470, -150);
        glEnd();

        File file = new File("score.txt");
        String scoreRecord[] = new String[3];
        Scanner scan;
        try {
            scan = new Scanner(file);
            int i = 0;
            while(scan.hasNextLine()){
                scoreRecord[i] = scan.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int x = 120, y = 170;
        for(int i = 0; i < scoreRecord.length; i++){
            char[] scorePoint = scoreRecord[i].toCharArray();
            for(int j = 0; j < scorePoint.length; j++){
                numSprite.getSpriteByNumber(scorePoint[j]).getTexture().bind();
                glBegin(GL_QUADS);
                    glTexCoord2f(0,1);
                    glVertex2i(x,y);
                    glTexCoord2f(0,0);
                    glVertex2i(x, y+30);
                    glTexCoord2f(1,0);
                    glVertex2i(x+17, y+30);
                    glTexCoord2f(1,1);
                    glVertex2i(x+17,y);
                glEnd();
                x+=15;
            }
            x=120;
            y-=35;
        }
    }

    public Texture loadMenuTexture(String textureName){
        try {
            return texture = TextureLoader.getTexture(
                    "PNG",
                    getClass().getClassLoader().getResourceAsStream(String.format("resources/texture/menu/%s.png", textureName))
            );
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCloseRequested(){
        return Display.isCloseRequested();
    }

    @Override
    public void destroy() {
        Display.destroy();
    }
}
