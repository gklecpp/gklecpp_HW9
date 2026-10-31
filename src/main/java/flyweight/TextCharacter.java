package flyweight;

public class TextCharacter {

    private char character;
    private CharacterProperties properties;

    public TextCharacter(char character, CharacterProperties properties){
        this.character = character;
        this.properties = properties;
    }

    public char getCharacter(){
        return character;
    }

    public CharacterProperties getProperties(){
        return properties;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setProperties(CharacterProperties properties) {
        this.properties = properties;
    }

    public void display(){
        System.out.println("Character: " + character + ", Font: " + properties.getFont() + ", Size: " + properties.getSize() + ", Color: " + properties.getColor());
    }
}
