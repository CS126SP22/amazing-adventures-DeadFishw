package student.adventure;

import student.server.GameStatus;

/**
 * Prints warnings and information of the game.
 */
public class Notice {
    /**
     * Print the warning that the character cannot go that direction
     * @param direction the direction that the character attempts to go
     */
    public static void warnCannotGo(String direction) {
        System.out.println("I can't go \"" +
                direction.trim().toLowerCase() + "\"!");
    }

    /**
     * Print the warning that the character cannot take an item
     * @param itemName the name of the item that the character attempts to take
     */
    public static void warnCannotTake(String itemName) {
        System.out.println("There is no \"" +
                itemName.trim().toLowerCase() + "\" in the room.");
    }

    /**
     * Print the warning that the character cannot drop an item
     * @param itemName the name of the item that the character attempts to drop
     */
    public static void warnCannotDrop(String itemName) {
        System.out.println("You don't have \"" +
                itemName.trim().toLowerCase() + "\"!");
    }

    /**
     * Print win message
     */
    public static void printWin() {
        System.out.println("Congratulations! You Win! Enjoy your Journey!");
    }

    /**
     * Print the description of the room.
     * @param room the room to be printed
     */
    public static void printRoom(Room room) {
        String output = room.getDescriptions() +
                "\nFrom here, you can go: ";
        for (int i = 0; i < room.getDirections().length; i++) {
            output += room.getDirections()[i].getDirectionName();
            if (i != room.getDirections().length - 1) {
                output += ", ";
            } else {
                output += "\n";
            }
        }
        if (!room.getItems().isEmpty()) {
            output += "Items visible: ";
            for (int i = 0; i < room.getItems().size(); i++) {
                output += room.getItems().get(i).getItemName();
                if (i != room.getItems().size() - 1) {
                    output += ", ";
                } else {
                    output += "\n";
                }
            }
        }
        System.out.print(output);
    }

    public static void warnDontUnderstand(String command) {
        System.out.println("I don't understand " + command);
    }
}
