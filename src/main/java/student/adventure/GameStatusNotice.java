package student.adventure;

import com.fasterxml.jackson.core.io.CharTypes;
import student.server.AdventureState;
import student.server.GameStatus;

import java.util.HashMap;

public class GameStatusNotice {
    /**
     * Print the warning that the character cannot go that direction
     * @param direction the direction that the character attempts to go
     * @param currentStatus the current status
     * @return the game status that warns cannot go
     */
    public static GameStatus warnCannotGo(String direction, GameStatus currentStatus) {
        String message = "I can't go \"" +
                direction.trim().toLowerCase() + "\"!";
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * Print the warning that the character cannot take an item
     * @param itemName the name of the item that the character attempts to take
     * @param currentStatus the current status
     * @return the game status that warns cannot take
     */
    public static GameStatus warnCannotTake(String itemName, GameStatus currentStatus) {
        String message = "There is no \"" +
                itemName.trim().toLowerCase() + "\" in the room.";
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * Print the warning that the character cannot drop an item
     * @param itemName the name of the item that the character attempts to drop
     * @param currentStatus the current status
     * @return the game status that warns cannot drop
     */
    public static GameStatus warnCannotDrop(String itemName, GameStatus currentStatus) {
        String message = "You don't have \"" +
                itemName.trim().toLowerCase() + "\"!";
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * Print win message
     * @param character the current character
     * @param currentStatus the current status
     * @return the game status that the player wins
     */
    public static GameStatus printWin(Character character, GameStatus currentStatus) {
        String message = "Congratulations! You Win! Enjoy your Journey! Throughout your journey, you have walked pass the following places: \n";
        for (int i = 0; i < character.getPath().size(); i++) {
            message += character.getPath().get(i) + "\n";
        }
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), new HashMap<>());
    }

    /**
     * Print the description of the room.
     * @param room the room to be printed
     * @param currentStatus the current status
     * @return the game status that prints the current room
     */
    public static GameStatus printRoom(Room room, GameStatus currentStatus) {
        String message = room.getDescriptions() +
                "\nFrom here, you can go: ";
        for (int i = 0; i < room.getDirections().length; i++) {
            message += room.getDirections()[i].getDirectionName();
            if (i != room.getDirections().length - 1) {
                message += ", ";
            } else {
                message += "\n";
            }
        }
        message += "Items visible: ";
        for (int i = 0; i < room.getItems().size(); i++) {
            message += room.getItems().get(i).getItemName();
            if (i != room.getItems().size() - 1) {
                message += ", ";
            } else {
                message += "\n";
            }
        }
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * print do not understand
     * @param command
     * @param currentStatus
     * @return the game status with message don't understand
     */
    public static GameStatus warnDontUnderstand(String command, GameStatus currentStatus) {
        String message = "I don't understand " + command;
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }
}
