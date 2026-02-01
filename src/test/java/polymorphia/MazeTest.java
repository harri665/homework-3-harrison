package polymorphia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polymorphia.characters.Adventurer;
import polymorphia.characters.Creature;
import polymorphia.characters.Guard;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    Maze maze;

    @BeforeEach
    void setUp() {
        maze = Maze.create2x2(List.of(
                new Adventurer("Bilbo"),
                new Creature("Ogre"),
                new Guard("Guard")
        ));
    }

    @Test
    void create2x2Grid() {
        assertEquals(4, maze.size());
    }

    @Test
    void hasLivingCreatures() {
        assertTrue(maze.hasLivingCreatures());
    }

    @Test
    void hasLivingAdventurers() {
        assertTrue(maze.hasLivingAdventurers());
    }

    @Test
    void testLivingCreatures() {
        // The maze was created with two creatures (Creature + Guard).
        assertEquals(2, maze.getLivingCreatures().size());
    }

    @Test
    void testToString() {
        System.out.println(maze.toString());

        assertTrue(maze.toString().contains("Northwest"));
        assertTrue(maze.toString().contains("Bilbo"));
        assertTrue(maze.toString().contains("Ogre"));
    }

    @Test
    void create3x3Grid() {
        Maze bigMaze = Maze.create3x3(List.of(
                new Adventurer("Frodo"),
                new Creature("Troll"),
                new Guard("Sentinel")
        ));
        assertEquals(9, bigMaze.size());
        assertTrue(bigMaze.toString().contains("Center"));
    }
}
