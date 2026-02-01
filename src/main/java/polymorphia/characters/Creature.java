package polymorphia.characters;

import polymorphia.Die;

public class Creature extends Character {
    static Double DEFAULT_INITIAL_HEALTH = 4.0;

    public Creature(String name) {
        super(name, DEFAULT_INITIAL_HEALTH, Die.sixSided());
    }

    public Creature(String name, Die die) {
        super(name, DEFAULT_INITIAL_HEALTH, die);
    }

    public Creature(String name, Double initialHealth) {
        super(name, initialHealth, Die.sixSided());
    }

    public Creature(String name, Double initialHealth, Die die) {
        super(name, initialHealth, die);
    }

    @Override
    public Boolean isCreature() {
        return true;
    }

    @Override
    public void doAction() {
        if (shouldFight()) {
            fight(getCurrentLocation().getRandomAdventurer());
        } else {
            move();
        }
    }

    protected Boolean shouldFight() {
        return adventurerInRoomWithMe();
    }

    private Boolean adventurerInRoomWithMe() {
        return getCurrentLocation().hasLivingAdventurers();
    }
}
