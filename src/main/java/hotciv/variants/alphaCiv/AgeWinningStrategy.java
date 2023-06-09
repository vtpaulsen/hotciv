package hotciv.variants.alphaCiv;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.WinningStrategy;

import java.util.HashMap;

public class AgeWinningStrategy implements WinningStrategy {
    @Override
    public Player findWinner(int age, HashMap<Position, CityImpl> cities) {
        if (age >= -3000) { return Player.RED; }
        else return null;
    }

    @Override
    public void incrementNumberOfWonBattles(Player player) {

    }
}
