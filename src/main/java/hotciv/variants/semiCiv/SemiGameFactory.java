package hotciv.variants.semiCiv;

import hotciv.standard.*;
import hotciv.variants.alphaCiv.StandardCheckIfMoveIsValidStrategy;
import hotciv.variants.alphaCiv.StandardMoveCountsStrategy;
import hotciv.variants.alphaCiv.StandardUnitProductionStrategy;
import hotciv.variants.alphaCiv.StandardUnitSpawnCheckStrategy;
import hotciv.variants.betaCiv.RegressiveAgingStrategy;
import hotciv.variants.deltaCiv.CustomWorldLayoutStrategy;
import hotciv.variants.epsilonCiv.AttackAndWinWinningStrategy;
import hotciv.variants.epsilonCiv.EpsilonUnitAttackStrategy;
import hotciv.variants.gammaCiv.GammaUnitActionsStrategy;

public class SemiGameFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new RegressiveAgingStrategy();
    }

    @Override
    public UnitActionsStrategy createUnitActionsStrategy() {
        return new SemiCivUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayout() {
        return new CustomWorldLayoutStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
            return new AttackAndWinWinningStrategy();
    }

    @Override
    public UnitAttackStrategy createUnitAttackStrategy() {
        return new EpsilonUnitAttackStrategy(new RandomDice());
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
