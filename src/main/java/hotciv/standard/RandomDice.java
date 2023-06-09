package hotciv.standard;

import java.util.Random;

public class RandomDice implements Dice {
    @Override
    public int rollDice() {
        return new Random().nextInt(6) + 1;
    }
}
