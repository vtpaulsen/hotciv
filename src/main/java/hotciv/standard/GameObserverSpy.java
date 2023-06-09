package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class GameObserverSpy implements GameObserver {
    private String lastCalledMethod = "none";


    @Override
    public void worldChangedAt(Position pos) {
        lastCalledMethod = "worldChangedAt";
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        lastCalledMethod = "turnEnds";
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        lastCalledMethod = "tileFocusChangedAt";
    }

    public String getLastCalledMethod(){
        return lastCalledMethod;
    }
}
