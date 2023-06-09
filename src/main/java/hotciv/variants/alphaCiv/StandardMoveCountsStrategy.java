package hotciv.variants.alphaCiv;

import hotciv.standard.MoveCountsStrategy;
import hotciv.standard.UnitImpl;

public class StandardMoveCountsStrategy implements MoveCountsStrategy {
    @Override
    public boolean moveCountsLeft(UnitImpl unit) {
        return unit.getMoveCount() == 1;
    }
}
