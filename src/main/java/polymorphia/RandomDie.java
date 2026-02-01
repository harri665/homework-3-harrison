package polymorphia;

import java.util.Random;

public class RandomDie extends Die {
    private final Random rand;
    private final int sides;

    public RandomDie(int sides) {
        this(sides, new Random());
    }

    public RandomDie(int sides, Random rand) {
        this.rand = rand;
        this.sides = sides;
    }

    @Override
    public int roll() {
        return rand.nextInt(sides) + 1;
    }
}
