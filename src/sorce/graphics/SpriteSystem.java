package sorce.graphics;

import java.util.HashMap;

public class SpriteSystem {
    private HashMap<Integer, Sprite> spriteByNumber = new HashMap<>();

    SpriteSystem(){
        for(Sprite sprite: Sprite.values()){
            if(sprite.getSpriteNumber() != null){
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
            }
        }
    }

    public Sprite getSpriteByNumber(int number){
        return spriteByNumber.getOrDefault(number, Sprite.EMPTY);
    }
}
