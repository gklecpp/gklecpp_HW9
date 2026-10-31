package flyweight;

import org.junit.jupiter.api.Test;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterFactoryTest {

    @Test
    void testSamePropertiesShareObject() {
        CharacterProperties first = CharacterFactory.getProperties("Arial", 12, "Red");
        CharacterProperties second = CharacterFactory.getProperties("Arial", 12, "Red");

        assertSame(first, second);
    }

    @Test
    void testDifPropertiesCreateDifObjects(){
        CharacterProperties first = CharacterFactory.getProperties("Arial", 12, "Red");

        CharacterProperties second = CharacterFactory.getProperties("Calibri", 14, "Blue");

        assertNotSame(first, second);
    }

    @Test
    void testCharacterPropertiesValues(){
        CharacterProperties properties = CharacterFactory.getProperties("Verdana", 16, "Black");

        assertEquals("Verdana", properties.getFont());
        assertEquals(16, properties.getSize());
        assertEquals("Black", properties.getColor());
    }

    @Test
    void testTextCharacterEditing(){
        CharacterProperties properties = CharacterFactory.getProperties("Arial", 12, "Red");

        TextCharacter character = new TextCharacter('H', properties);

        character.setCharacter('J');

        assertEquals('J', character.getCharacter());
    }

    @Test
    void testCharacterFormattingChange(){
        CharacterProperties original = CharacterFactory.getProperties("Arial", 12, "Red");
        CharacterProperties updated = CharacterFactory.getProperties("Calibri", 14, "Blue");

        TextCharacter character = new TextCharacter('H', original);

        character.setProperties(updated);

        assertSame(updated, character.getProperties());
    }

    @Test
    void testDocumentSaveandLoad() throws Exception{
        java.nio.file.Path file = java.nio.file.Files.createTempFile("flyweight-test", ".txt");

        try{
            Document document = new Document();
            document.addCharacter('H', "Arial", 12, "Red");
            document.addCharacter('i', "Calibri", 14, "Blue");

            document.saveToFile(file.toString());

            Document loadedDocument = new Document();
            loadedDocument.loadFromFile(file.toString());

            java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();

            java.io.PrintStream originalOut = System.out;

            try {
                System.setOut(new java.io.PrintStream(output));
                loadedDocument.displayDocument();
            }   finally {
                System.setOut(originalOut);
            }

            String result = output.toString();

            assertTrue(result.contains("Character: H, Font: Arial, Size: 12, Color: Red"));
            assertTrue(result.contains("Character: i, Font: Calibri, Size: 14, Color: Blue"));
        } finally {
            java.nio.file.Files.deleteIfExists(file);
        }
    }
}
