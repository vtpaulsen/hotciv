package hotciv.standard;

import hotciv.variants.alphaCiv.StandardUnitProductionStrategy;

public interface GameFactory {

    AgingStrategy createAgingStrategy();
    UnitActionsStrategy createUnitActionsStrategy();
    WorldLayoutStrategy createWorldLayout();
    WinningStrategy createWinningStrategy();
    UnitAttackStrategy createUnitAttackStrategy();
    MoveCountsStrategy createMoveCountsStrategy();
    CheckIfMoveIsValidStrategy createCheckIfMoveIsValidStrategy();
    UnitProductionStrategy createUnitProductionStrategy();
    UnitSpawnCheckStrategy createUnitSpawnCheckStrategy();
}
