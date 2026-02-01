package polymorphia.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polymorphia.Die;

public class Guard extends Creature {
    static Double DEFAULT_INITIAL_HEALTH = 3.0;

    private static final Logger logger = LoggerFactory.getLogger(Guard.class);

    public Guard(String name) {
        super(name, DEFAULT_INITIAL_HEALTH, Die.sixSided());
    }

    public Guard(String name, Die die) {
        super(name, DEFAULT_INITIAL_HEALTH, die);
    }

    @Override
    public void doAction() {
        if (shouldFight()) {
            fight(getCurrentLocation().getRandomAdventurer());
        }
        logger.debug("{} stayed put", name);
    }
}
