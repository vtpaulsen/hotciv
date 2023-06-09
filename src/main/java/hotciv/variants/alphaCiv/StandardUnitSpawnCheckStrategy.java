package hotciv.variants.alphaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitSpawnCheckStrategy;

public class StandardUnitSpawnCheckStrategy implements UnitSpawnCheckStrategy {

    private GameImpl game;

    @Override
    public boolean checkIfValidUnitPosition(GameImpl game, Position pos, Position city) {
        if (checkIfOutOfBounds(pos)) {
            return false;
        }
        if (game.getUnitAt(pos) == null) {
            return !game.getTileAt(pos).getTypeString().equals(GameConstants.MOUNTAINS) && !game.getTileAt(pos).getTypeString().equals(GameConstants.OCEANS)
                    && game.getUnitAt(pos) == null && (game.getCityAt(pos) == null || game.getCityAt(pos).getOwner() != game.getCityAt(city).getOwner());
        } else return false;
    }

    @Override
    public boolean checkIfOutOfBounds(Position spawn) {
        return (spawn.getRow() < 0 || spawn.getColumn() < 0 || spawn.getColumn() > 15 || spawn.getRow() > 15);
    }
}
