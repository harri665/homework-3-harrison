package polymorphia;

public abstract class Die {
    public abstract int roll();

    public static Die sixSided() {
        return new RandomDie(6);
    }
}

