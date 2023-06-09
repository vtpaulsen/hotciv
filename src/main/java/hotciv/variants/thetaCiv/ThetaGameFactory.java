package hotciv.variants.thetaCiv;

import hotciv.standard.*;
import hotciv.stub.ThetaWorldLayoutStrategy;
import hotciv.variants.alphaCiv.AgeWinningStrategy;
import hotciv.variants.alphaCiv.LinearAgingStrategy;
import hotciv.variants.alphaCiv.TheAttackerAlwaysWinsStrategy;
import hotciv.variants.deltaCiv.CustomWorldLayoutStrategy;
import hotciv.variants.gammaCiv.GammaUnitActionsStrategy;
import hotciv.variants.thetaCiv.*;

public class ThetaGameFactory implements GameFactory {
    @Override
    public AgingStrategy createAgingStrategy() {
        return new LinearAgingStrategy();
    }

    @Override
    public UnitActionsStrategy createUnitActionsStrategy() {
        return new ThetaUnitActionsStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayout() {
        return new ThetaWorldLayoutStrategy();
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
        return new ThetaMoveCountsStrategy();
    }

    @Override
    public CheckIfMoveIsValidStrategy createCheckIfMoveIsValidStrategy() {
        return new ThetaCheckIfMoveIsValidStrategy();
    }

    @Override
    public UnitProductionStrategy createUnitProductionStrategy() {
        return new ThetaUnitProductionStrategy();
    }

    @Override
    public UnitSpawnCheckStrategy createUnitSpawnCheckStrategy() {
        return new ThetaUnitSpawnCheckStrategy();
    }
}
