package polymorphia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polymorphia.characters.Character;

import java.util.List;
import java.util.Random;

public class Polymorphia {
    static Logger logger = LoggerFactory.getLogger(Polymorphia.class);

    private final Maze maze;
    private Integer turnCount = 0;
    private final Random rand;

    public Polymorphia() {
        this(Maze.create2x2(), new Random());
    }

    public Polymorphia(Maze maze) {
        this(maze, new Random());
    }

    public Polymorphia(Maze maze, Random rand) {
        this.maze = maze;
        this.rand = rand;
    }

    public String toString() {
        return "Polymorphia MAZE: turn " + turnCount + "\n" + maze.toString();
    }

    // Game is over when all creatures are killed
// or all adventurers are killed
    public Boolean isOver() {
        return !hasLivingCreatures() || !hasLivingAdventurers();
    }

    public Boolean hasLivingCreatures() {
        return maze.hasLivingCreatures();
    }

    public Boolean hasLivingAdventurers() {
        return maze.hasLivingAdventurers();
    }

    public void playTurn() {
        if (turnCount == 0) {
            logger.info("Starting play...");
        }
        turnCount += 1;

        // Process all the characters in random order
        List<Character> characters = getLivingCharacters();
        while (!characters.isEmpty()) {
            int index = rand.nextInt(characters.size());
            Character character = characters.get(index);
            if (character.isAlive()) {
                // Polymorphism: each Character subtype provides its own action behavior.
                character.doAction();
            }
            characters.remove(index);
        }
    }

    public List<Character> getLivingCharacters() {
        return maze.getLivingCharacters();
    }


    public void play() {
        while (!isOver()) {
            playTurn();
            logger.info("{}", this);
        }
        logger.info("The game ended after {} turns.", turnCount);
        String eventDescription;
        if (hasLivingAdventurers()) {
            eventDescription = "The adventurers won! Left standing are: " + getAdventurerNames() + "\n";
        } else if (hasLivingCreatures()) {
            eventDescription = "The creatures won. Boo! Left standing are: " + getCreatureNames() + "\n";
        } else {
            eventDescription = "No team won! Everyone died!\n";
        }
        logger.info(eventDescription);
    }

    String getAdventurerNames() {
        return String.join(", ", getLivingCharacters().stream().map(Object::toString).toList());
    }

    String getCreatureNames() {
        return String.join(", ", getLivingCreatures().stream().map(Object::toString).toList());
    }

    public List<Character> getLivingCreatures() {
        return maze.getLivingCreatures();
    }

    public Character getWinner() {
        if (!isOver() || !hasLivingCharacters()) {
            // No one has won yet or no one won -- all died
            return null;
        }
        return getLivingCharacters().getFirst();
    }

    private boolean hasLivingCharacters() {
        return !getLivingCharacters().isEmpty();
    }
}
