package hotciv.variants.epsilonCiv;

import hotciv.framework.*;
import hotciv.standard.Dice;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitAttackStrategy;
import hotciv.utility.*;

public class EpsilonUnitAttackStrategy implements UnitAttackStrategy {

    private final Dice dice;

    public EpsilonUnitAttackStrategy(Dice dice){
        this.dice = dice;
    }

    @Override
    public boolean attack(GameImpl game, Position from, Position to) {
        if (game.getUnitAt(to) == null){
            return true;
        }


        Player playerFrom = game.getUnitAt(from).getOwner();
        Player playterTo = game.getUnitAt(to).getOwner();

        int combinedAttackStrength = (Utility2.getFriendlySupport(game, from, playerFrom)
                + game.getUnitAt(from).getAttackingStrength()) * Utility2.getTerrainFactor(game, from);

        int combinedDefenseStrength = (Utility2.getFriendlySupport(game, to, playterTo)
                + game.getUnitAt(to).getDefensiveStrength()) * Utility2.getTerrainFactor(game, to);

        int d1 = dice.rollDice();
        int d2 = dice.rollDice();
        return combinedAttackStrength * d1 > combinedDefenseStrength * d2;
    }
}
