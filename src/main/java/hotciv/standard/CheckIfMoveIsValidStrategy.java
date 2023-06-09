package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;

import java.util.HashMap;

public interface CheckIfMoveIsValidStrategy {

    boolean checkIfTheMoveIsValid(Game game, Position from, Position to);
    boolean checkIfOutOfBounds(Position to);
}
