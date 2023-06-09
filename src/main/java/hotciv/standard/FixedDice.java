package hotciv.standard;

import java.util.Random;

public class FixedDice implements Dice {
    @Override
    public int rollDice() {
        return 4;
    }

}
