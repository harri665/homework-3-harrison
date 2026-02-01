package polymorphia;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedDieTest {

    @Test
    void testFixedValueAlwaysRollsSame() {
        Die die = new FixedDie(4);
        assertEquals(4, die.roll());
        assertEquals(4, die.roll());
    }

    @Test
    void testSequenceCycles() {
        Die die = new FixedDie(List.of(1, 6));
        assertEquals(1, die.roll());
        assertEquals(6, die.roll());
        assertEquals(1, die.roll());
    }
}
