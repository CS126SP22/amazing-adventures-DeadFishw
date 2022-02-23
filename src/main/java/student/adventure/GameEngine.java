package student.adventure;
import java.util.Scanner;

/**
 * The game engine of the game.
 */
public class GameEngine {
    Layout layout;
    public GameEngine(Layout layout) {
        this.layout = layout;
    }

    /**
     * Run the game given the character.
     * @param character The character to start the game
     */
    public void runGame(Character character) {
        Notice.printRoom(character.getCurrentRoom());
        boolean isGameRunning = true;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (command.trim().equalsIgnoreCase("quit") ||
                command.trim().equalsIgnoreCase("exit")) {
            isGameRunning = false;
        } else if (command.startsWith("go ")) {
            character.goSomewhere(command.substring(3).trim());
        } else if (command.trim().equalsIgnoreCase("examine")) {

        } else if (command.trim().startsWith("take ")) {
            character.take(command.substring(4).trim());
        } else if (command.startsWith("drop ")) {
            character.drop(command.substring(4).trim());
        } else {
            System.out.println("I don't understand " + command);
        }
        if (character.getCurrentRoom().getName().equals(character.getLayout().getEndingRoom())) {
            isGameRunning = false;
            Notice.printWin();
        }
        if (!isGameRunning) {
            return;
        }
        runGame(character);
    }

    /**
     * Initialize character and the game.
     */
    public void start() {
        Character character = new Character(layout);
        runGame(character);
    }
}
