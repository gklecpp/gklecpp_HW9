package flyweight;

import org.w3c.dom.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private final List<TextCharacter> characters = new ArrayList<>();

    public void addCharacter(char character, String font, int size, String color){
        CharacterProperties properties = CharacterFactory.getProperties(font, size, color);
        characters.add(new TextCharacter(character, properties));
    }

    public void editCharacter(int index, char newCharacter){
        if (index >= 0 && index < characters.size()){
            characters.get(index).setCharacter(newCharacter);
        }
    }

    public void changeProperties(int index, String font, int size, String color){
        if (index >= 0 && index < characters.size()){
            CharacterProperties properties = CharacterFactory.getProperties(font, size, color);
            characters.get(index).setProperties(properties);
        }
    }

    public void displayDocument(){
        for (TextCharacter character : characters){
            character.display();
        }
    }

    public void saveToFile(String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (TextCharacter character : characters){
                CharacterProperties properties = character.getProperties();

                writer.write((int) character.getCharacter() + "," + properties.getFont() + "," + properties.getSize() + "," + properties.getColor());
                writer.newLine();
            }
            System.out.println("Document saved to " + filename);
        } catch (IOException e){
            System.out.println("Error saving document: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            List<TextCharacter> loadedCharacters = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null){
                String[] parts = line.split("," , -1);

                if (parts.length != 4){
                    throw new IOException("Invalid document format");
                }

                char character = (char) Integer.parseInt(parts[0]);
                String font = parts[1];
                int size = Integer.parseInt(parts[2]);
                String color = parts[3];

                CharacterProperties properties = CharacterFactory.getProperties(font, size, color);
                loadedCharacters.add(new TextCharacter(character, properties));
            }
            characters.clear();
            characters.addAll(loadedCharacters);

            System.out.println("Document loaded from " + filename);
        } catch(IOException | NumberFormatException e){
            System.out.println("Error loading document: " + e.getMessage());
        }
    }
}
