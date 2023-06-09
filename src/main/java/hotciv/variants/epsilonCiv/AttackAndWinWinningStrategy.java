package hotciv.variants.epsilonCiv;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.WinningStrategy;

import java.util.HashMap;

public class AttackAndWinWinningStrategy implements WinningStrategy {
    private HashMap<Player, Integer> attackWins = new HashMap<>();


    @Override
    public Player findWinner(int age, HashMap<Position, CityImpl> cities) {
        for (Player p: attackWins.keySet()) {
            if (attackWins.get(p)>2){
                return p;
            }
        }
        return null;
    }

    @Override
    public void incrementNumberOfWonBattles(Player player) {
        if (attackWins.get(player)==null){
            attackWins.put(player, 1);
        } else {
            attackWins.put(player, attackWins.get(player)+1);
        }
    }

    public void getPlayerWins(Player p){
        attackWins.get(p);
    }

}
