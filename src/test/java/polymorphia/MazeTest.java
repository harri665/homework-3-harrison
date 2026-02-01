package polymorphia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze();
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
        // In current state of the Maze class, it creates and places two Creatures. This is hidden in the test and therefore, not a great
        //  test or implementation of the Maze class.
        assertEquals(2, maze.getLivingCreatures().size());
    }

    @Test
    void testToString() {
        System.out.println(maze.toString());

        assertTrue(maze.toString().contains("Northwest"));
        assertTrue(maze.toString().contains("Bilbo"));
        assertTrue(maze.toString().contains("Ogre"));
    }
}
