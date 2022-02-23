package student.adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The room object in the map.
 */
public class Room {
    private String name;
    private String description;
    private Direction[] directions;
    private List<Item> items;

    public Room(){}
    public Room(String setName) {
        name = setName;
    }
    public Direction[] getDirections() {
        return directions;
    }
    public String getDescriptions() {
        return description;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return name.equals(room.name);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    /**
     * Remove the item with the given name.
     * @param itemName the name of the item to be removed
     * @return the item object that is removed; null if the item is not found.
     */
    public Item removeItem(String itemName) {
        for (Item item: items) {
            if (item.getItemName().equalsIgnoreCase(itemName.trim())) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    /**
     * Add an item to the room.
     * @param item the item to be added to the room.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    public void setDescription(String descriptions) {
        this.description = descriptions;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
