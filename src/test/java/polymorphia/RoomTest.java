package polymorphia;

import org.junit.jupiter.api.Test;
import polymorphia.characters.Adventurer;
import polymorphia.characters.Creature;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    void getRandomNeighbor() {
        Room room = new Room("onlyRoom");
        Room neighbor = new Room("neighbor");
        room.addNeighbor(neighbor);

        assertEquals(room.getRandomNeighbor(), neighbor);
    }

    @Test
    void testGetRandomNeighborOnRoomWithNoNeighbors() {
        Room room = new Room("onlyRoom");

        assertNull(room.getRandomNeighbor());
    }

    @Test
    void testToString() {
        Room room = new Room("onlyRoom");
        room.add(new Adventurer("Bilbo"));
        room.add(new Creature("Ogre"));

        System.out.println(room);

        assertTrue(room.toString().contains("onlyRoom"));
        assertTrue(room.toString().contains("Bilbo"));
        assertTrue(room.toString().contains("Ogre"));
    }
}
