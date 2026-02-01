package polymorphia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomDieTest {

    @Test
    void testRollInRange() {
        Die die = new RandomDie(6);
        int result = die.roll();
        assertTrue(result >= 1 && result <= 6, "RandomDie roll should be between 1 and 6");
    }
}
