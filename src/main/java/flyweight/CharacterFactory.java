package flyweight;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    private static final Map<String, CharacterProperties> propertiesMap = new HashMap<>();

    public static CharacterProperties getProperties(String font, int size, String color){
        String key = font + "-" + size + "-" + color;

        if (!propertiesMap.containsKey(key)){
            propertiesMap.put(key, new CharacterProperties(font, size, color));
        }

        return propertiesMap.get(key);
    }

    public static int getTotalProperties(){
        return propertiesMap.size();
    }
}
