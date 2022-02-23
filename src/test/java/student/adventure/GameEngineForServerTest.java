package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import student.server.AdventureState;
import student.server.Command;
import student.server.GameStatus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GameEngineForServerTest {
    GameEngineForServer engine;
    Layout layout;
    ObjectMapper mapper;
    @Before
    public void setUp() throws IOException {
        // This is run before every test.
        File file = new File("src/test/java/student/adventure/siebel.json");
        mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
        engine = new GameEngineForServer(new Character(layout), new GameStatus(false, 0, "", "", "", new AdventureState(), new HashMap<>()), layout);
    }

    @Test
    public void testGoSomewhereEast() {
        engine.runGame(new Command("go", "east"));
        assertEquals(engine.getCharacter().getCurrentRoom().getName(), "SiebelEntry");
        assertEquals(engine.getCharacter().getCurrentRoom().getItems().get(0).getItemName(), "coin2");
    }

    @Test
    public void testGoSomewhereCannotGo() {
        engine.runGame(new Command("go", "north"));
        assertEquals("I can't go \"north\"!", engine.getGameStatus().getMessage());
    }

    @Test
    public void testGoSomewhereRandomString() {
        engine.runGame(new Command("go", "some random direction"));
        assertEquals("I can't go \"some random direction\"!", engine.getGameStatus().getMessage());
    }

    @Test
    public void testGoSomewhereIgnoresCase() {
        engine.runGame(new Command("go", "EAsT"));
        assertEquals(engine.getCharacter().getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testGoSomewhereIgnoresSpace() {
        engine.runGame(new Command("go", "   east        "));
        assertEquals(engine.getCharacter().getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testTakeCoin() {
        engine.runGame(new Command("take", "coin1"));
        assertEquals(engine.getCharacter().getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testTakeNoItem() {
        engine.runGame(new Command("take", "random item"));
        assertEquals("There is no \"random item\" in the room.", engine.getGameStatus().getMessage());
    }

    @Test
    public void testTakeIgnoresCase() {
        engine.runGame(new Command("take", "COiN1"));
        assertEquals(engine.getCharacter().getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testTakeIgnoresSpace() {
        engine.runGame(new Command("take", "        coin1       "));
        assertEquals(engine.getCharacter().getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testDropCoin1() {
        engine.runGame(new Command("take", " coin1"));
        engine.runGame(new Command("drop", " coin1"));
        assertEquals(engine.getCharacter().getItems().size(), 0);
    }

    @Test
    public void testDropNoItem() {
        engine.runGame(new Command("drop", " coins"));
        assertEquals("You don't have \"coins\"!", engine.getGameStatus().getMessage());
    }


    @Test
    public void testDropIgnoresCase() {
        engine.runGame(new Command("take", " coin1"));
        engine.runGame(new Command("drop", " cOiN1"));
        assertEquals(engine.getCharacter().getItems().size(), 0);
    }

    @Test
    public void testDropIgnoresSpace() {
        engine.runGame(new Command("take", " coin1"));
        engine.runGame(new Command("drop", "        coin1       "));
        assertEquals(engine.getCharacter().getItems().size(), 0);
    }
}