package hotciv.broker;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnit implements Unit {
    @Override
    public String getTypeString() {
        return GameConstants.SETTLER;
    }

    @Override
    public Player getOwner() {
        return Player.GREEN;
    }

    @Override
    public int getMoveCount() {
        return 12452;
    }

    @Override
    public int getDefensiveStrength() {
        return -10;
    }

    @Override
    public int getAttackingStrength() {
        return 9;
    }

    @Override
    public String getId() {
        return null;
    }
}
