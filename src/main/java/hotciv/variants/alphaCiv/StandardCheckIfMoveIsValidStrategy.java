package hotciv.variants.alphaCiv;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.CheckIfMoveIsValidStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class StandardCheckIfMoveIsValidStrategy implements CheckIfMoveIsValidStrategy {
    @Override
    public boolean checkIfTheMoveIsValid(Game game, Position from, Position to) {
        // Units may not move out of bounds
        if (checkIfOutOfBounds(to)) {
            return false;
        }

        // Units may not move to tiles with types "mountains" or "oceans".
        if (game.getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) || game.getTileAt(to).getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }

        if (game.getUnitAt(to) != null) {
            if (game.getUnitAt(from).getOwner().equals(game.getUnitAt(to).getOwner())) {
                return false;
            }
        }

        // The positions from and to may not be the same position.
        if (from.getRow() != to.getRow() || from.getColumn() != to.getColumn()) {
            // Units may not move more than 1 step.
            return Math.abs(from.getRow() - to.getRow()) < 2 && Math.abs(from.getColumn() - to.getColumn()) < 2;
        }

        checkIfOutOfBounds(to);

        return false;
    }

    @Override
    public boolean checkIfOutOfBounds(Position to) {
        return (to.getRow() < 0 || to.getColumn() < 0 || to.getColumn() > 15 || to.getRow() > 15);
    }
}
