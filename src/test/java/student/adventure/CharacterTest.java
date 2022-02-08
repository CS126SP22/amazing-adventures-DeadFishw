package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CharacterTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Character character;
    Layout layout;
    ObjectMapper mapper;
    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        // This is run before every test.
        File file = new File("src/main/resources/siebel.json");
        mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
        character = new Character(layout);
    }
    @Test
    public void testGoSomewhereEast() {
        character.goSomewhere("go east");
        assertEquals(character.getCurrentRoom().getName(), "SiebelEntry");
        assertEquals(character.getCurrentRoom().getItems().get(0).getItemName(), "coin2");
    }

    @Test
    public void testGoSomewhereCannotGo() {
        character.goSomewhere("go north");
        assertEquals("I can't go \"north\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereRandomString() {
        character.goSomewhere("go some random direction");
        assertEquals("I can't go \"some random direction\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereIgnoresCase() {
        character.goSomewhere("go EAsT");
        assertEquals(character.getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testGoSomewhereIgnoresSpace() {
        character.goSomewhere("go        east       ");
        assertEquals(character.getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testTakeCoin() {
        character.take("take coin1");
        assertEquals(character.getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testTakeNoItem() {
        character.take("take random item");
        assertEquals("There is no \"random item\" in the room.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeIgnoresCase() {
        character.take("take CoIN1");
        assertEquals(character.getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testTakeIgnoresSpace() {
        character.take("take        coin1       ");
        assertEquals(character.getItems().get(0).getItemName(), "coin1");
    }

    @Test
    public void testDropCoin1() {
        character.drop("drop coin1");
        assertEquals(character.getItems().size(), 0);
    }

    @Test
    public void testDropNoItem() {
        character.drop("drop coins");
        assertEquals("You don't have \"coins\"!", outputStreamCaptor.toString().trim());
    }


    @Test
    public void testDropIgnoresCase() {
        character.take("take coin1");
        character.drop("drop cOiN1");
        assertEquals(character.getItems().size(), 0);
    }

    @Test
    public void testDropIgnoresSpace() {
        character.take("take coin1");
        character.drop("drop        coin1       ");
        assertEquals(character.getItems().size(), 0);
    }
}
