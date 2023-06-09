package hotciv.variants.semiCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variants.gammaCiv.GammaUnitActionsStrategy;

public class SemiCivUnitActionStrategy extends GammaUnitActionsStrategy {

    @Override
    public void unitAction(GameImpl game, Position pos) {
        String unitType = game.getUnitAt(pos).getTypeString();
        if (unitType.equals(GameConstants.SETTLER)) {
            Player owner = game.getUnitAt(pos).getOwner();
            game.getCitiesMap().put(pos, new CityImpl(owner));
            game.getUnitsMap().remove(pos);
        }
    }
}
