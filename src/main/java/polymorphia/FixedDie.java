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
            throw emptyRollsError();
        }
        this.rolls = new ArrayList<>(rolls);
    }

    private static IllegalArgumentException emptyRollsError() {
        return new IllegalArgumentException("Rolls list must not be empty");
    }

    @Override
    public int roll() {
        int value = rolls.get(index);
        index = (index + 1) % rolls.size();
        return value;
    }
}
