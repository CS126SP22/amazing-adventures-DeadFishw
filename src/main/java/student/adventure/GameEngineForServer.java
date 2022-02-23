package student.adventure;

import student.server.Command;
import student.server.GameStatus;

import java.util.Scanner;

public class GameEngineForServer {
    Layout layout;
    Character character;
    GameStatus gameStatus;

    public GameEngineForServer(Character character, GameStatus gameStatus, Layout layout) {
        this.character = character;
        this.gameStatus = gameStatus;
        this.layout = layout;
        updateStatus();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void updateStatus() {
        gameStatus = GameStatusNotice.printRoom(character.getCurrentRoom(), gameStatus);
        gameStatus.setCommandOptions(character.getCommandOptions());
    }

    /**
     * Run the game given the character.
     * @param command The character to start the game
     */
    public void runGame(Command command) {
        Notice.printRoom(character.getCurrentRoom());
        if (command.getCommandName().equals("go")) {
            if (!character.goSomewhere(command.getCommandValue())) {
                gameStatus = GameStatusNotice.warnCannotGo(command.getCommandValue(), gameStatus);
            } else {
                updateStatus();
            }
        } else if (command.getCommandName().trim().equalsIgnoreCase("examine")) {
            gameStatus = GameStatusNotice.printRoom(character.getCurrentRoom(), gameStatus);
        } else if (command.getCommandName().trim().equals("take")) {
            if (!character.take(command.getCommandValue())) {
                gameStatus = GameStatusNotice.warnCannotTake(command.getCommandValue(), gameStatus);
            } else {
                updateStatus();
            }
        } else if (command.getCommandName().equals("drop")) {
            if (!character.drop(command.getCommandValue())) {
                gameStatus = GameStatusNotice.warnCannotDrop(command.getCommandValue(), gameStatus);
            } else {
                updateStatus();
            }
        } else {
            gameStatus = GameStatusNotice.warnDontUnderstand(command.getCommandName() + " " + command.getCommandValue(), gameStatus);
        }
        if (character.getCurrentRoom().getName().equals(character.getLayout().getEndingRoom())) {
            gameStatus = GameStatusNotice.printWin(gameStatus);
        }
    }
}
