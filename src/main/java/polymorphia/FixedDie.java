package polymorphia;

import java.util.ArrayList;
import java.util.List;

public class FixedDie extends Die {
    private final List<Integer> rolls;
    private int index = 0;

    public FixedDie(int fixedValue) {
        this(List.of(fixedValue));
    }

    public FixedDie(List<Integer> rolls) {
        if (rolls == null || rolls.isEmpty()) {
            throw new IllegalArgumentException("Rolls list must not be empty");
        }
        this.rolls = new ArrayList<>(rolls);
    }

    @Override
    public int roll() {
        int value = rolls.get(index);
        index = (index + 1) % rolls.size();
        return value;
    }
}
