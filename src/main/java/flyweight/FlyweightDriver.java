package flyweight;

public class FlyweightDriver {

    public static void main(String[] args){

        Document document = new Document();
        String text = "HelloWorldCS5800";

        // create text with 4 different styles
        for (int i = 0; i < text.length(); i++){
            char character = text.charAt(i);

            if (i < 5) {
                document.addCharacter(character, "Arial", 12, "Red");
            } else if(i < 10){
                document.addCharacter(character, "Calibri", 14, "Blue");
            } else if(i < 12){
                document.addCharacter(character, "Verdana", 16, "Black");
            } else{
                    document.addCharacter(character, "Arial", 14, "Blue");
            }
        }
        System.out.println("Original Document: ");
        document.displayDocument();

        System.out.println("\nTotal unique properties: " + CharacterFactory.getTotalProperties());

        // test editing a character
        document.editCharacter(0, 'J');

        System.out.println("\nAfter Editing: ");
        document.displayDocument();

        // restore original character
        document.editCharacter(0, 'H');

        // test changing character formatting
        document.changeProperties(0, "Calibri", 14, "Blue");

        System.out.println("\nAfter Changing Properties: ");
        document.displayDocument();

        // save doc
        document.saveToFile("document.txt");

        // load doc from file
        Document loadedDocument = new Document();
        loadedDocument.loadFromFile("document.txt");

        System.out.println("\nLoaded Document: ");
        loadedDocument.displayDocument();

        System.out.println("\nTotal unique properties: " + CharacterFactory.getTotalProperties());

    }
}
