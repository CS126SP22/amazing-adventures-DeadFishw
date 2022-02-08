package student.adventure;

/**
 * The directions the character goes.
 */
public class Direction {
    private String directionName;
    private String room;

    public Direction() { }
    public Direction(String setDirection, String setRoom) {
        directionName = setDirection;
        room = setRoom;
    }

    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }

    public void setDirectionName(String direction) {
        this.directionName = direction;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
