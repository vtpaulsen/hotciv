package hotciv.standard;

import hotciv.framework.Position;

public interface UnitSpawnCheckStrategy {
    boolean checkIfValidUnitPosition(GameImpl game, Position pos, Position city);
    boolean checkIfOutOfBounds(Position position);

}
