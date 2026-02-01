package polymorphia.characters;

import polymorphia.Die;
import polymorphia.Room;

abstract public class Character {
    static final Double DEFAULT_INITIAL_HEALTH = 2.0;
    static final Double HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME = 0.5;
    static final Double HEALTH_LOST_IN_MOVING_ROOMS = 0.25;

    protected String name;
    private Double health;

    Die die = Die.sixSided();

    private Room currentLocation;

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public Character(String name) {
        this(name, DEFAULT_INITIAL_HEALTH);
    }

    public Character(String name, Double initialHealth) {
        this.name = name;
        this.health = initialHealth;
    }

    public void enterRoom(Room room) {
        this.currentLocation = room;
    }

    public String toString() {
        return getName() + "(health: " + getHealth() + ")";
    }

    public void loseHealth(Double healthPoints) {
        if (health <= 0) {
            return;     // already dead, probably called for mandatory health loss for having a fight
        }

        health -= healthPoints;

        if (health <= 0) {
            System.out.println(name + " just died!");
        }
    }

    public Double getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public Boolean isAlive() {
        return getHealth() > 0;
    }

    public void loseFightDamage(double fightDamage) {
        loseHealth(fightDamage);
    }

    public Boolean isAdventurer() {
        return false;
    }

    public Boolean isCreature() {
        return false;
    }

    void fight(Character opponent) {
        Integer adventurerRoll = die.roll();
        Integer creatureRoll = die.roll();
        System.out.println(getName() + " is fighting " + opponent);

        System.out.println(getName() + " rolled " + adventurerRoll);
        System.out.println(opponent + " rolled " + creatureRoll);

        if (adventurerRoll > creatureRoll) {
            opponent.loseFightDamage(adventurerRoll - creatureRoll);
        } else if (creatureRoll > adventurerRoll) {
            loseFightDamage(creatureRoll - adventurerRoll);
        }

        loseHealth(Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME);
        opponent.loseHealth(Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME);
    }

    public void doAction() {
        System.out.println("Doing nothing for action for Character " + getName());
    }

    protected void move() {
        Room nextLocation = getCurrentLocation().getRandomNeighbor();
        System.out.println(getName() + " moved from " + getCurrentLocation().getName() + " to " + nextLocation.getName());
        nextLocation.enter(this);
        loseHealth(HEALTH_LOST_IN_MOVING_ROOMS);
    }
}
