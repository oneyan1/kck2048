package sorce.graphics;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

public enum Sprite {
    CELL2(2), CELL4(4), CELL8(8),
    CELL16(16), CELL32(32), CELL64(64),
    CELL128(128), CELL256(256), CELL512(512),
    CELL1024(1024), CELL2048(2048), EMPTY("empty");

    private Texture texture;
    private Integer spriteNumber;

    Sprite(String textureName){
        this(textureName, null);
    }

    Sprite(int spriteNumber){
        this(String.valueOf(spriteNumber), spriteNumber);
    }

    Sprite(String textureName, Integer spriteNumber){
        this.spriteNumber = spriteNumber;
        try {
            texture = TextureLoader.getTexture(
                    "PNG",
                    getClass().getClassLoader().getResourceAsStream(String.format("resources/texture/%s.png", textureName))
            );
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Texture getTexture(){
        return this.texture;
    }

    public Integer getSpriteNumber(){
        return spriteNumber;
    }
}
