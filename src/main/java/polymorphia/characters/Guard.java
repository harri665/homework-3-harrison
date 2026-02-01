package polymorphia.characters;

public class Guard extends Creature {
    static Double DEFAULT_INITIAL_HEALTH = 3.0;

    public Guard(String name) {
        super(name, DEFAULT_INITIAL_HEALTH);
    }

    @Override
    public void doAction() {
        if (shouldFight()) {
            fight(getCurrentLocation().getRandomAdventurer());
        }
        System.out.println(name + " stayed put");
    }
}
