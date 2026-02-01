package polymorphia.characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventurerTest {

    @Test
    public void testConstructor() {
        Adventurer hero = new Adventurer("Hero");
        assertEquals("Hero", hero.getName());
        assertEquals(Adventurer.DEFAULT_INITIAL_HEALTH, hero.getHealth());
    }

}