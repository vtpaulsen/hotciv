package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

public interface UnitActionsStrategy {

    void unitAction(GameImpl game, Position pos);

}
