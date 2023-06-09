package hotciv.variants.betaCiv;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.WinningStrategy;

import java.util.HashMap;

public class ConquerWinningStrategy implements WinningStrategy {

    @Override
    public Player findWinner(int age, HashMap<Position ,CityImpl> cities) {
        Boolean ba = false;
        Player winner = null;
        for (CityImpl c : cities.values()) {
            if (ba == false){
                winner = c.getOwner();
                ba = true;
            } else {
                if (winner != c.getOwner()){
                    return null;
                }
            }
        }
        return winner;
    }

    @Override
    public void incrementNumberOfWonBattles(Player player) {

    }
}

