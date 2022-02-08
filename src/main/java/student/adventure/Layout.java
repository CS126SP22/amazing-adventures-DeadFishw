package student.adventure;

/**
 * The layout (map) of the game.
 */
public class Layout {
    String startingRoom;
    String endingRoom;
    Room[] rooms;
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }


    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
    }
}
