package hotciv.variants.betaCiv;

import hotciv.standard.*;
import hotciv.variants.alphaCiv.*;

public class BetaGameFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new RegressiveAgingStrategy();
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
        return new ConquerWinningStrategy();
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
