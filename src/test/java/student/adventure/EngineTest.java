package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class EngineTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    GameEngine engine;
    Layout layout;
    ObjectMapper mapper;
    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        // This is run before every test.
        File file = new File("src/main/resources/siebel.json");
        mapper = new ObjectMapper();
        engine = new GameEngine();
        layout = mapper.readValue(file, Layout.class);
    }
    @Test
    public void testRunGameExit() {
        String testExit = "exit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGameExitIgnoresCase() {
        String testExit = "EXiT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGameExitIgnoresSpace() {
        String testExit = "       exit      ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGameQuit() {
        String testExit = "quit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGameQuitIgnoresCase() {
        String testExit = "QuIT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGameQuitIgnoresSpace() {
        String testExit = "        quit        ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
    }

    @Test
    public void testRunGamePrint() {
        String testExit = "quit";
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.start(layout);
        assertEquals("You are on Matthews, outside the Siebel Center\n" +
                "From here, you can go: East\n" +
                "Items visible: coin1", outputStreamCaptor.toString().trim());
    }

}