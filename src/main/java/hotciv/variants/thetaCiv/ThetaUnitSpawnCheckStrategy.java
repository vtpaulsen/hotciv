package hotciv.variants.thetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitSpawnCheckStrategy;
import hotciv.variants.alphaCiv.StandardUnitSpawnCheckStrategy;

public class ThetaUnitSpawnCheckStrategy implements UnitSpawnCheckStrategy {

    private UnitSpawnCheckStrategy standardUnitSpawn;

    public ThetaUnitSpawnCheckStrategy() {
        this.standardUnitSpawn = new StandardUnitSpawnCheckStrategy();
    }

    @Override
    public boolean checkIfValidUnitPosition(GameImpl game, Position pos, Position city) {
        if (checkIfOutOfBounds(pos)) {
            return false;
        }
        if (game.getCityAt(city).getProduction().equals(ThetaGameConstants.SANDWORM)) {
            if (game.getUnitAt(pos) == null) {
                return game.getTileAt(pos).getTypeString().equals(ThetaGameConstants.DESERT) &&
                        !game.getTileAt(pos).getTypeString().equals(GameConstants.MOUNTAINS)
                        && !game.getTileAt(pos).getTypeString().equals(GameConstants.OCEANS)
                        && game.getUnitAt(pos) == null
                        && game.getCityAt(pos) == null;
            } else return false;
        }
        return standardUnitSpawn.checkIfValidUnitPosition(game, pos, city);
    }

    @Override
    public boolean checkIfOutOfBounds(Position spawn) {
        return (spawn.getRow() < 0 || spawn.getColumn() < 0 || spawn.getColumn() > 15 || spawn.getRow() > 15);

    }
}
