package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

import java.util.HashMap;
import java.util.Map;

public interface WinningStrategy {

    Player findWinner(int age, HashMap<Position, CityImpl> cities);

    void incrementNumberOfWonBattles(Player player);
}
