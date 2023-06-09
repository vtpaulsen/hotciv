package hotciv.variants.alphaCiv;

import hotciv.framework.GameConstants;
import hotciv.standard.UnitProductionStrategy;

public class StandardUnitProductionStrategy implements UnitProductionStrategy {

    @Override
    public int getProductionCost(String unitType) {
        if (unitType.equals(GameConstants.ARCHER)) {
            return 10;
        } else if (unitType.equals(GameConstants.LEGION)) {
            return 15;
        } else return 30;
    }

}
