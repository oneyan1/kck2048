package sorce.graphics;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import sorce.logic.Constant;
import sorce.logic.Field;


public class GraphicDesktop implements Graphic {
    private SpriteSystem sprite;

    public GraphicDesktop(){
        initOpengl();
        sprite = new SpriteSystem();
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
    public void draw(Field field, int score) {
        glClear(GL_COLOR_BUFFER_BIT);
        for(int i = 0; i < Constant.COUNT_CELL_X; i++){
            for(int j= 0; j < Constant.COUNT_CELL_Y; j++){
                //drawCell(Constant.CELL_SIZE*i, Constant.CELL_SIZE*j, field.getStateCell(i,j));
            }
        }
        Display.update();
        Display.sync(60);
    }

    public void drawCell(int x, int y, int state){
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

    public boolean isCloseRequested(){
        return Display.isCloseRequested();
    }

    @Override
    public void destroy() {
        Display.destroy();
    }
}
