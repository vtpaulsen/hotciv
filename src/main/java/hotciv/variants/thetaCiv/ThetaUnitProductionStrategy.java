package hotciv.variants.thetaCiv;

import hotciv.standard.UnitProductionStrategy;
import hotciv.variants.alphaCiv.StandardUnitProductionStrategy;

public class ThetaUnitProductionStrategy implements UnitProductionStrategy {

    UnitProductionStrategy standardUnitProduction;

    public ThetaUnitProductionStrategy(){
        this.standardUnitProduction = new StandardUnitProductionStrategy();
    }

    @Override
    public int getProductionCost(String unitType) {
        if (unitType.equals(ThetaGameConstants.SANDWORM))
            return 30;
        else {
            return standardUnitProduction.getProductionCost(unitType);
        }
    }
}
