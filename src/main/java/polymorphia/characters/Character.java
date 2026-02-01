package polymorphia.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polymorphia.Die;
import polymorphia.Room;

abstract public class Character {
    static final Double DEFAULT_INITIAL_HEALTH = 2.0;
    static final Double HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME = 0.5;
    static final Double HEALTH_LOST_IN_MOVING_ROOMS = 0.25;

    private static final Logger logger = LoggerFactory.getLogger(Character.class);

    protected String name;
    private Double health;

    private final Die die;

    private Room currentLocation;

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public Character(String name) {
        this(name, DEFAULT_INITIAL_HEALTH, Die.sixSided());
    }

    public Character(String name, Double initialHealth) {
        this(name, initialHealth, Die.sixSided());
    }

    public Character(String name, Double initialHealth, Die die) {
        // Dependency Injection: the die is passed in rather than constructed here.
        this.name = name;
        this.health = initialHealth;
        this.die = die;
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
            logger.info("{} just died!", name);
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

    protected Die getDie() {
        return die;
    }

    void fight(Character opponent) {
        Integer myRoll = getDie().roll();
        Integer opponentRoll = opponent.getDie().roll();
        logger.debug("{} is fighting {}", getName(), opponent);

        logger.debug("{} rolled {}", getName(), myRoll);
        logger.debug("{} rolled {}", opponent, opponentRoll);

        if (myRoll > opponentRoll) {
            opponent.loseFightDamage(myRoll - opponentRoll);
        } else if (opponentRoll > myRoll) {
            loseFightDamage(opponentRoll - myRoll);
        }

        loseHealth(Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME);
        opponent.loseHealth(Character.HEALTH_LOST_IN_FIGHT_REGARDLESS_OF_OUTCOME);
    }

    public void doAction() {
        logger.debug("Doing nothing for action for Character {}", getName());
    }

    protected void move() {
        Room nextLocation = getCurrentLocation().getRandomNeighbor();
        logger.debug("{} moved from {} to {}", getName(), getCurrentLocation().getName(), nextLocation.getName());
        nextLocation.enter(this);
        loseHealth(HEALTH_LOST_IN_MOVING_ROOMS);
    }
}
