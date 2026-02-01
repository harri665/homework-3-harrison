package polymorphia.characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polymorphia.Room;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    Character joe;

    @BeforeEach
    void setUp() {
        joe = new Adventurer("Joe");
    }

    @Test
    void testToString() {
        System.out.println(joe);

        assertTrue(joe.toString().contains("Joe"));
    }

    @Test
    void isAlive() {
        assertTrue(joe.isAlive());
    }

    @Test
    void testLoseHealthAndDeath() {
        joe.loseHealth(2.0);
        assertEquals(Adventurer.DEFAULT_INITIAL_HEALTH - 2.0, joe.getHealth());

        // Lose enough health to die
        joe.loseHealth(10.0);
        assertFalse(joe.isAlive());
    }

    @Test
    void testFightingMandatoryLossOfHalfAPoint() {
        Creature ogre = new Creature("Ogre");
        Double joesHealthBeforeFight = joe.getHealth();
        Double ogreHealthBeforeFight = ogre.getHealth();

        joe.fight(ogre);

        System.out.println("After the fight, Joe's health is: " + joe.getHealth());
        assertTrue(joe.getHealth() <= (joesHealthBeforeFight - Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME));
        assertTrue(ogre.getHealth() <= (ogreHealthBeforeFight - Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME));
    }

    @Test
    void testMovingRoomLossOfQuarterPoint() {
        Room currentRoom = new Room("currentRoom");
        Room newRoom = new Room("newRoom");
        currentRoom.addNeighbor(newRoom);
        joe.enterRoom(currentRoom);

        // Since nothing is in the current room with Joe, he should move to the new room
        joe.doAction();

        System.out.println("After moving rooms, Joe's health is: " + joe.getHealth());
        assertEquals(Adventurer.DEFAULT_INITIAL_HEALTH - Character.HEALTH_LOST_IN_MOVING_ROOMS, joe.getHealth());
        assertEquals(newRoom, joe.getCurrentLocation());
    }
}
