package hotciv.standard;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface UnitAttackStrategy {
    boolean attack(GameImpl game, Position from, Position to);
}
