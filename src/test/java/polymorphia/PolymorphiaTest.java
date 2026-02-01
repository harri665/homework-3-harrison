package polymorphia;

import org.junit.jupiter.api.Test;
import polymorphia.characters.Adventurer;
import polymorphia.characters.Character;
import polymorphia.characters.Creature;
import polymorphia.characters.Guard;
import polymorphia.Die;
import polymorphia.FixedDie;

import java.util.List;
import java.util.Random;

public class PolymorphiaTest {

    @Test
    public void testGame() {
        Polymorphia game = new Polymorphia();
        game.play();
        assert game.isOver();
        assert !game.hasLivingAdventurers() || !game.hasLivingCreatures();
    }

    @Test
    void testFairPlay() {
        int adventurerWins = 0;
        int creatureWins = 0;
        int numTies = 0;
        int TOTAL_GAMES = 100;
        for (int i = 0; i < TOTAL_GAMES; i++) {
            Polymorphia game = new Polymorphia();

            game.play();
            if (game.getWinner() == null) {
                numTies++;
            } else if (game.getWinner().isCreature()) {
                creatureWins++;
            } else {
                adventurerWins++;
            }
        }
        System.out.println("Adventurers won " + adventurerWins + " and creatures won " + creatureWins);
        System.out.println("There were " + numTies + " games with no winners");
    }

    @Test
    public void test2x2GameWithTwoAdventurersAndFiveCreatures() {
        Die fixedDie = new FixedDie(3);
        List<Character> characters = List.of(
                new Adventurer("A1", fixedDie),
                new Adventurer("A2", fixedDie),
                new Creature("C1", fixedDie),
                new Creature("C2", fixedDie),
                new Creature("C3", fixedDie),
                new Guard("G1", fixedDie),
                new Guard("G2", fixedDie)
        );
        Maze maze = Maze.create2x2(characters, new Random(1));
        Polymorphia game = new Polymorphia(maze, new Random(2));

        game.play();

        assert game.isOver();
    }

    @Test
    public void test3x3GameWithThreeAdventurersAndFourCreatures() {
        Die fixedDie = new FixedDie(4);
        List<Character> characters = List.of(
                new Adventurer("A1", fixedDie),
                new Adventurer("A2", fixedDie),
                new Adventurer("A3", fixedDie),
                new Creature("C1", fixedDie),
                new Creature("C2", fixedDie),
                new Guard("G1", fixedDie),
                new Guard("G2", fixedDie)
        );
        Maze maze = Maze.create3x3(characters, new Random(3));
        Polymorphia game = new Polymorphia(maze, new Random(4));

        game.play();

        assert game.isOver();
    }
}
