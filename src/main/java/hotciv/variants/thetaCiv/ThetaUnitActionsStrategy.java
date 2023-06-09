package hotciv.variants.thetaCiv;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitActionsStrategy;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;
import hotciv.variants.gammaCiv.GammaUnitActionsStrategy;


public class ThetaUnitActionsStrategy implements UnitActionsStrategy {

    private UnitActionsStrategy unitActionsStrategyGamma;

    public ThetaUnitActionsStrategy() {
        this.unitActionsStrategyGamma = new GammaUnitActionsStrategy();
    }

    @Override
    public void unitAction(GameImpl game, Position pos) {
        if (game.getUnitAt(pos).getTypeString().equals(ThetaGameConstants.SANDWORM)) {
            UnitImpl unit = game.getUnitAt(pos);
            if (unit.getTypeString().equals(ThetaGameConstants.SANDWORM)) {
                Iterable<Position> i = Utility.get8neighborhoodOf(pos);
                for (Position p : i) {
                    if (game.getUnitAt(p) != null) {
                        if (!unit.getOwner().equals(game.getUnitAt(p).getOwner())) {
                            game.removeUnitAt(p);
                        }
                    }
                }
            }
        } else {
            unitActionsStrategyGamma.unitAction(game, pos);
        }
    }
}
