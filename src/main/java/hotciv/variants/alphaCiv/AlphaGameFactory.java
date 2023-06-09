package hotciv.variants.alphaCiv;

import hotciv.standard.*;

public class AlphaGameFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new LinearAgingStrategy();
    }

    @Override
    public UnitActionsStrategy createUnitActionsStrategy() {
        return new NoUnitActionsStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayout() {
        return new StandardWorldLayoutStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AgeWinningStrategy();
    }

    @Override
    public UnitAttackStrategy createUnitAttackStrategy() {
        return new TheAttackerAlwaysWinsStrategy();
    }

    @Override
    public MoveCountsStrategy createMoveCountsStrategy() {
        return new StandardMoveCountsStrategy();
    }

    @Override
    public CheckIfMoveIsValidStrategy createCheckIfMoveIsValidStrategy() {
        return new StandardCheckIfMoveIsValidStrategy();
    }

    @Override
    public UnitProductionStrategy createUnitProductionStrategy() {
        return new StandardUnitProductionStrategy();
    }

    @Override
    public UnitSpawnCheckStrategy createUnitSpawnCheckStrategy() {
        return new StandardUnitSpawnCheckStrategy();
    }
}
