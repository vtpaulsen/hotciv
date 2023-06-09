package hotciv.variants.thetaCiv;

import hotciv.standard.MoveCountsStrategy;
import hotciv.standard.UnitImpl;
import hotciv.variants.alphaCiv.StandardMoveCountsStrategy;

public class ThetaMoveCountsStrategy implements MoveCountsStrategy {

    private final MoveCountsStrategy standardMoveCountsStrategy;

    public ThetaMoveCountsStrategy(){
        this.standardMoveCountsStrategy = new StandardMoveCountsStrategy();
    }

    @Override
    public boolean moveCountsLeft(UnitImpl unit) {
        if (unit.getTypeString().equals(ThetaGameConstants.SANDWORM)){
            return unit.getMoveCount()>0;
        } else {
            return standardMoveCountsStrategy.moveCountsLeft(unit);
        }
    }
}
