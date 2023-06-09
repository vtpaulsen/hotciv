package hotciv.variants.epsilonCiv;

import hotciv.standard.*;
import hotciv.variants.alphaCiv.*;

public class FixedEpsilonGameFactory implements GameFactory {
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
        return new AttackAndWinWinningStrategy();
    }

    @Override
    public UnitAttackStrategy createUnitAttackStrategy() {
        return new EpsilonUnitAttackStrategy(new FixedDice());
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
