package sorce.graphics;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

public enum NumberSprite {
    NUM1('1'), NUM2('2'),NUM3('3'),NUM4('4'),NUM5('5'),
    NUM6('6'),NUM7('7'),NUM8('8'),NUM9('9'),NUM0('0');

    private Texture texture;
    private char textureNumber;

    NumberSprite(char textureNumber){
        this.textureNumber = textureNumber;
        try {
            texture = TextureLoader.getTexture(
                    "PNG",
                    getClass().getClassLoader().getResourceAsStream(String.format("resources/texture/menu/number/"+ textureNumber  +".png"))
            );
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Texture getTexture(){
        return this.texture;
    }

    public char getSpriteNumber(){
        return textureNumber;
    }
}
