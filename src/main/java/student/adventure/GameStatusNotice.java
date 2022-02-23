package student.adventure;

import student.server.AdventureState;
import student.server.GameStatus;

public class GameStatusNotice {
    /**
     * Print the warning that the character cannot go that direction
     * @param direction the direction that the character attempts to go
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
     */
    public static GameStatus warnCannotDrop(String itemName, GameStatus currentStatus) {
        String message = "You don't have \"" +
                itemName.trim().toLowerCase() + "\"!";
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * Print win message
     */
    public static GameStatus printWin(GameStatus currentStatus) {
        String message = "Congratulations! You Win! Enjoy your Journey!";
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }

    /**
     * Print the description of the room.
     * @param room the room to be printed
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

    public static GameStatus warnDontUnderstand(String command, GameStatus currentStatus) {
        String message = "I don't understand " + command;
        return new GameStatus(false, currentStatus.getId(), message, currentStatus.getImageUrl(),
                currentStatus.getVideoUrl(), currentStatus.getState(), currentStatus.getCommandOptions());
    }
}
