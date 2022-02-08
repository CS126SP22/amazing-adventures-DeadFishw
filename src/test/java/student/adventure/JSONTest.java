
package student.adventure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.io.*;

import static org.junit.Assert.*;

public class JSONTest {
    @Test
    public void testBlankJSON() throws IOException {
        File file = new File("src/main/resources/Blank.json");
        boolean hasException = false;
        try {
            Layout layout = new ObjectMapper().readValue(file, Layout.class);
        } catch (FileNotFoundException e) {
            hasException = true;
        }
        assertTrue(hasException);
    }

    @Test
    public void testBadJSON() throws IOException {
        File file = new File("src/test/java/student/adventure/badJson.json");
        boolean hasException = false;
        try {
            Layout layout = new ObjectMapper().readValue(file, Layout.class);
        } catch (com.fasterxml.jackson.databind.exc.MismatchedInputException e) {
            hasException = true;
        }
        assertTrue(hasException);
    }

    @Test
    public void testGoodJSON() throws IOException {
        File file = new File("src/test/java/student/adventure/goodJson.json");
        Layout layout = new ObjectMapper().readValue(file, Layout.class);
        assertEquals(layout.startingRoom, "MatthewsStreet");
        assertEquals(layout.endingRoom, "Siebel1314");
        assertEquals(layout.rooms.length, 1);
        assertEquals(layout.rooms[0].getName(), "MatthewsStreet");
    }
}