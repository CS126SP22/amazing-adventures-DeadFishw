import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Character;
import student.adventure.GameEngine;
import student.adventure.Layout;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Wishing you good luck on your Adventure!
        GameEngine engine = new GameEngine();
        File file = new File("src/main/resources/siebel.json");
        ObjectMapper mapper = new ObjectMapper();
        Layout layout = mapper.readValue(file, Layout.class);

        // Wishing you good luck on your Adventure!
        engine.start(layout);
    }
}
