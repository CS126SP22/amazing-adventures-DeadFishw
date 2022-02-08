package student.adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * The character of the game.
 */
public class Character {
    private Room currentRoom;
    private List<Item> items = new ArrayList<>();
    private Layout layout;

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
     * @param command the command taken in
     */
    public void take(String command) {
        String itemToTake = command.substring(4).trim().toLowerCase();
        for (Item item: currentRoom.getItems()) {
            if (item.getItemName().equals(itemToTake)) {
                items.add(currentRoom.removeItem(itemToTake));
                return;
            }
        }
        Notice.warnCannotTake(itemToTake);
    }

    /**
     * drop the item indicated by the command; if fails print warning.
     * @param command the command taken in
     */
    public void drop(String command) {
        String itemToDrop = command.substring(4);
        for (Item item: items) {
            if (item.getItemName().equals(itemToDrop.trim().toLowerCase())) {
                currentRoom.addItem(item);
                items.remove(item);
                return;
            }
        }
        Notice.warnCannotDrop(itemToDrop);
    }

    /**
     * go the direction indicated by the command; if fails print warning.
     * @param command the command taken in
     */
    public void goSomewhere(String command) {
        String directionToGo = command.substring(3).trim();
        for (Direction direction: currentRoom.getDirections()) {
            if (directionToGo.trim().equalsIgnoreCase(direction.getDirectionName())) {
                for (Room room : layout.getRooms()) {
                    if (direction.getRoom().equalsIgnoreCase(room.getName())) {
                        setCurrentRoom(room);
                        return;
                    }
                }
            }
        }
        Notice.warnCannotGo(directionToGo);
    }
}
