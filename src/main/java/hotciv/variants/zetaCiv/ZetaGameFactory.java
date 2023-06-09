package hotciv.variants.zetaCiv;

import hotciv.standard.*;
import hotciv.variants.alphaCiv.*;
import hotciv.variants.betaCiv.ConquerWinningStrategy;
import hotciv.variants.epsilonCiv.AttackAndWinWinningStrategy;

public class ZetaGameFactory implements GameFactory {
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
        return new ZetaWinningStrategy(new ConquerWinningStrategy(), new AttackAndWinWinningStrategy());
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
