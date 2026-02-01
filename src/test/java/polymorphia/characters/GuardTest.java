package polymorphia.characters;

import org.junit.jupiter.api.Test;
import polymorphia.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuardTest {

    @Test
    public void testConstructor() {
        Character guard = new Guard("Guard");
        assertEquals("Guard", guard.getName());
        assertEquals(Guard.DEFAULT_INITIAL_HEALTH, guard.getHealth());
    }

    @Test
    void testGuardNotMoving() {
        Room currentRoom = new Room("currentRoom");
        Room newRoom = new Room("newRoom");
        currentRoom.addNeighbor(newRoom);
        Character guard = new Guard("Guard");
        guard.enterRoom(currentRoom);

        // The guard should not move to the new room
        guard.doAction();

        assertEquals(currentRoom, guard.getCurrentLocation());
    }
}
