package student.adventure;

public class Notice {
    public static void warnCannotGo(String direction) {
        System.out.println("I can't go \"" +
                direction.trim().toLowerCase() + "\"!");
    }

    public static void warnCannotTake(String itemName) {
        System.out.println("There is no \"" +
                itemName.trim().toLowerCase() + "\" in the room.");
    }

    public static void warnCannotDrop(String itemName) {
        System.out.println("You don't have \"" +
                itemName.trim().toLowerCase() + "\"!");
    }

    public static void printWin() {
        System.out.println("Congratulations! You Win! Enjoy your Journey!");
    }

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
        output += "Items visible: ";
        for (int i = 0; i < room.getItems().size(); i++) {
            output += room.getItems().get(i).getItemName();
            if (i != room.getItems().size() - 1) {
                output += ", ";
            } else {
                output += "\n";
            }
        }
        System.out.print(output);
    }
}
