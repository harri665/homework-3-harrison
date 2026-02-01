package polymorphia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DieTest {

    @Test
    void testRoll() {
        Die die = Die.sixSided();
        int result = die.roll();
        assertTrue(result >= 1 && result <= 6, "Die roll should be between 1 and 6");
    }

}