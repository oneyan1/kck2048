package sorce.graphics;

import java.util.HashMap;

public class NumberSpriteSystem {
    private HashMap<Character, NumberSprite> spriteByNumber = new HashMap<>();

    NumberSpriteSystem(){
        for(NumberSprite sprite: NumberSprite.values()){
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
        }
    }

    public NumberSprite getSpriteByNumber(Character number){
        return spriteByNumber.getOrDefault(number, null);
    }
}

