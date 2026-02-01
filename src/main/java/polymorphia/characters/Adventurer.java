package polymorphia.characters;


import polymorphia.Room;

public class Adventurer extends Character {
    static Double DEFAULT_INITIAL_HEALTH = 5.0;

    public Adventurer(String name) {
        super(name, DEFAULT_INITIAL_HEALTH);
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