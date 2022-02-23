package student.adventure;

import student.server.GameStatus;

import java.util.*;

/**
 * The character of the game.
 */
public class Character {
    private Room currentRoom;
    private List<Item> items = new ArrayList<>();
    private Layout layout;
    private List<String> path = new ArrayList<>();
    public Character(Layout layout) {
        for (Room startingRoom: layout.rooms) {
            if (startingRoom.getName().equals(layout.getStartingRoom())) {
                currentRoom = startingRoom;
            }
        }
        if (currentRoom == null) {
            currentRoom =layout.rooms[0];
        }
        this.layout = layout;
    }
    public List<String> getPath() {
        return path;
    }
    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Character() { }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * take the item indicated by the command; if fails print warning.
     * @param itemToTake the command taken in
     */
    public boolean take(String itemToTake) {
        for (Item item: currentRoom.getItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToTake.trim())) {
                items.add(currentRoom.removeItem(itemToTake));
                return true;
            }
        }
        Notice.warnCannotTake(itemToTake);
        return false;
    }

    /**
     * drop the item indicated by the command; if fails print warning.
     * @param itemToDrop the command taken in
     */
    public boolean drop(String itemToDrop) {
        for (Item item: items) {
            if (item.getItemName().trim().equalsIgnoreCase(itemToDrop.trim())) {
                currentRoom.addItem(item);
                items.remove(item);
                return true;
            }
        }
        Notice.warnCannotDrop(itemToDrop);
        return false;
    }

    /**
     * go the direction indicated by the command; if fails print warning.
     * @param directionToGo the command taken in
     */
    public boolean goSomewhere(String directionToGo) {
        for (Direction direction: currentRoom.getDirections()) {
            if (directionToGo.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : layout.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        setCurrentRoom(room);
                        path.add(room.getName());
                        return true;
                    }
                }
            }
        }
        Notice.warnCannotGo(directionToGo);
        return false;
    }

    /**
     * Get the command options of the character at the point.
     *
     * @return the commands that the character can receive at the point.
     */
    public Map<String, List<String>> getCommandOptions() {
        Map<String, List<String>> commandOptions =  new HashMap<>();
        List emptyStringList = new ArrayList<String>();
        emptyStringList.add("");
        commandOptions.put("examine", emptyStringList);
        commandOptions.put("reset", emptyStringList);
        if (currentRoom.getDirections().length > 0) {
            List<String> directions = new ArrayList<>();
            if (currentRoom.getDirections() != null && currentRoom.getDirections().length != 0) {
                for(Direction direction: currentRoom.getDirections()) {
                    directions.add(direction.getDirectionName());
                }
            }
            commandOptions.put("go", directions);
        }
        if (currentRoom.getItems().size() > 0) {
            List<String> itemStrings = new ArrayList<>();
            if (currentRoom.getItems() != null && currentRoom.getItems().size() != 0) {
                for(Item item: currentRoom.getItems()) {
                    itemStrings.add(item.getItemName());
                }
            }
            commandOptions.put("take", itemStrings);
        }
        if (this.getItems().size() > 0) {
            List<String> itemStrings = new ArrayList<>();
            if (items != null && items.size() != 0) {
                for(Item item: items) {
                    itemStrings.add(item.getItemName());
                }
            }
            commandOptions.put("drop", itemStrings);
        }
        return commandOptions;
    }
}
