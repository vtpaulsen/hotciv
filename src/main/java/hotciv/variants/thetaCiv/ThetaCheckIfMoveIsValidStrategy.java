package hotciv.variants.thetaCiv;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.CheckIfMoveIsValidStrategy;
import hotciv.variants.alphaCiv.StandardCheckIfMoveIsValidStrategy;

import java.util.Objects;

public class ThetaCheckIfMoveIsValidStrategy implements CheckIfMoveIsValidStrategy {

    private final CheckIfMoveIsValidStrategy standardCheckIfMoveCountIsValidStrategy;

    public ThetaCheckIfMoveIsValidStrategy(){
        this.standardCheckIfMoveCountIsValidStrategy = new StandardCheckIfMoveIsValidStrategy();
    }


    @Override
    public boolean checkIfTheMoveIsValid(Game game, Position from, Position to) {
        if (game.getUnitAt(from).getTypeString().equals(ThetaGameConstants.SANDWORM)) {
            return (game.getUnitAt(from).getMoveCount() > 0
                    && Objects.equals(game.getTileAt(to).getTypeString(), ThetaGameConstants.DESERT));
        } else {
            return standardCheckIfMoveCountIsValidStrategy.checkIfTheMoveIsValid(game, from, to);
        }
    }

    @Override
    public boolean checkIfOutOfBounds(Position to) {
        return standardCheckIfMoveCountIsValidStrategy.checkIfOutOfBounds(to);
    }
}
