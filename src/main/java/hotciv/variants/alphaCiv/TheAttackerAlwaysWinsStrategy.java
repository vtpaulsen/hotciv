package hotciv.variants.alphaCiv;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitAttackStrategy;

public class TheAttackerAlwaysWinsStrategy implements UnitAttackStrategy {
    @Override
    public boolean attack(GameImpl game, Position from, Position to) {
        return true;
    }
}
