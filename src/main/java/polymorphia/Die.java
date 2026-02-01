package polymorphia;

import java.util.Random;

public class Die {
    private final Random rand;
    private final int sides;


    public Die(int sides) {
        this.rand = new Random();
        this.sides = sides;
    }

    public static Die sixSided() {
        return new Die(6);
    }

    public int roll() {
        return rand.nextInt(sides) + 1;
    }
}

