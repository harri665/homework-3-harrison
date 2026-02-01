package polymorphia.characters;


import polymorphia.Die;
import polymorphia.Room;

// Inheritance: Adventurer extends Character to reuse and specialize shared behavior.
public class Adventurer extends Character {
    static Double DEFAULT_INITIAL_HEALTH = 5.0;

    public Adventurer(String name) {
        super(name, DEFAULT_INITIAL_HEALTH, Die.sixSided());
    }

    public Adventurer(String name, Die die) {
        super(name, DEFAULT_INITIAL_HEALTH, die);
    }

    public void enterRoom(Room room) {
        if (getCurrentLocation() != null) {
            if (getCurrentLocation().equals(room)) {
                return;
            }
            getCurrentLocation().remove(this);
        }
        super.enterRoom(room);
    }

    public void doAction() {
        if (shouldFight()) {
            fight(getCurrentLocation().getRandomCreature());
        } else {
            move();
        }
    }

    Boolean shouldFight() {
        return creatureInRoomWithMe();
    }

    Boolean creatureInRoomWithMe() {
        return getCurrentLocation().hasLivingCreatures();
    }

    @Override
    public Boolean isAdventurer() {
        return true;
    }
}