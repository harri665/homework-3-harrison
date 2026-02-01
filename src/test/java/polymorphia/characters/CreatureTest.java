package polymorphia.characters;

import org.junit.jupiter.api.Test;
import polymorphia.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatureTest {

    @Test
    public void testConstructor() {
        Creature orc = new Creature("Orc");
        System.out.println(orc);
        assertEquals("Orc", orc.getName());
        assertEquals(Creature.DEFAULT_INITIAL_HEALTH, orc.getHealth());
    }

    @Test
    void testMovingRoomLossOfQuarterPoint() {
        Room currentRoom = new Room("currentRoom");
        Room newRoom = new Room("newRoom");
        currentRoom.addNeighbor(newRoom);
        Creature orc = new Creature("Orc");
        orc.enterRoom(currentRoom);

        // Since nothing is in the current room with Joe, he should move to the new room
        orc.doAction();

        System.out.println("After moving rooms, Orc's health is: " + orc.getHealth());
        assertEquals(Creature.DEFAULT_INITIAL_HEALTH - Character.HEALTH_LOST_IN_MOVING_ROOMS, orc.getHealth());
        assertEquals(newRoom, orc.getCurrentLocation());
    }
}