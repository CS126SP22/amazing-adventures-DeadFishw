package student.adventure;

import student.server.AdventureState;
import student.server.GameStatus;
import student.adventure.MessagePrinter;

/**
 * AdventureGame instance for playing in the server
 */
public class AdventureGameInServer {
    private Character character;
    private GameStatus status;
    private Layout map;

    public AdventureGameInServer(Character character, GameStatus gameStatus, Layout map) {
        this.character = character;
        this.status = gameStatus;
        this.map = map;
    }


    public Character getCharacter() {
        return character;
    }

    public GameStatus getGameStatus() {
        return status;
    }

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

    public void setMap(Layout map) {
        this.map = map;
    }

    public void examine() {
        status.setMessage(character.toString());
    }

    /**
     * Taking the items in the Room
     *
     * @param command the command to take item("take xxx")
     */
    public void take(String command) {
        if (character.getCurrentRoom().getItems() == null) {
            status = MessagePrinter.printCannotTake(status, command);
            return;
        }
        for (Item item: character.getCurrentRoom().getItems()) {
            if (item.getItemName().equalsIgnoreCase(command.trim())) {
                character.getItems().add(item);
                character.getCurrentRoom().getItems().remove(item);
                status = new GameStatus(false, status.getId(), character.toString(), status.getImageUrl(), status.getVideoUrl(), new AdventureState(), character.getCommandOptions());
                return;
            }
        }
        status = MessagePrinter.printCannotTake(status, command);
    }

    /**
     * Dropping the character.getItems() in the Room
     *
     * @param command the command to drop item("drop xxx")
     */
    public void drop( String command) {
        if (character.getItems() == null) {
            status = MessagePrinter.printDoNotHaveItemDrop(status, command);
            return;
        }
        for (Item item: character.getItems()) {
            if (item.getItemName().equalsIgnoreCase(command.trim())) {
                character.getItems().remove(item);
                character.getCurrentRoom().getItems().add(item);
                status = new GameStatus(false, status.getId(), character.toString(), status.getImageUrl(), status.getVideoUrl(), new AdventureState(), character.getCommandOptions());
                return;
            }
        }
        status = MessagePrinter.printDoNotHaveItemDrop(status, command);
    }

    /**
     * Moving around in the map
     *
     * @param command the command to go somewhere item("go xxx")
     * @return true for game win and false for game continue.
     */
    public boolean goSomewhere(String command) {
        if (isGameOver(command)) {
                status = MessagePrinter.printWinMessage(status);
            return true;
        }
        for (Direction direction: character.getCurrentRoom().getDirections()) {
            if (command.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : map.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                            character.setCurrentRoom(room);
                            status = new GameStatus(false, status.getId(), character.toString(),
                                    character.getCurrentRoom().getImageURL(), "",
                                    new AdventureState(),
                                    character.getCommandOptions());
                            return false;
                    }
                }
                return false;
            }
        }

        status = MessagePrinter.printNoDirection(status, command);
        return false;
    }

    /**
     * Check if the game is over (win or lose)
     * @param command the command to take
     * @return true for over
     */
    public boolean isGameOver(String command) {
        return command.trim().equalsIgnoreCase("Fight the dragon") &&
                character.getCurrentRoom().getName().equalsIgnoreCase("Nest of the Dragon");
    }

    /**
     * send the message that the command is not understood.
     * @param command the command that the engine does not understand
     */
    public void sayDoNotUnderstand(String command) {
        status = MessagePrinter.printDoNotUnderstand(status, command);
    }

}
