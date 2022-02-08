package student.adventure;
import java.util.Scanner;

public class GameEngine {

    public void runGame(Character character) {
        Notice.printRoom(character.getCurrentRoom());
        boolean isGameRunning = true;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (command.trim().equalsIgnoreCase("quit") ||
                command.trim().equalsIgnoreCase("exit")) {
            isGameRunning = false;
        } else if (command.startsWith("go ")) {
            character.goSomewhere(command);
        } else if (command.trim().equalsIgnoreCase("examine")) {
            
        } else if (command.trim().startsWith("take ")) {
            character.take(command);
        } else if (command.startsWith("drop ")) {
            character.drop(command);
        } else {
            System.out.println("I don't understand " + command);
        }
        if (!isGameRunning) {
            return;
        }
        runGame(character);
    }

    public void start(Layout layout) {
        Character character = new Character(layout);

        character.setLayout(layout);
        runGame(character);
    }
}
