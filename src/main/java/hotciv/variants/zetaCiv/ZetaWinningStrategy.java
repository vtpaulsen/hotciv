package hotciv.variants.zetaCiv;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.WinningStrategy;

import java.util.HashMap;

public class ZetaWinningStrategy implements WinningStrategy {

    private WinningStrategy winningStrategyBeta;
    private WinningStrategy winningStrategyEpsilon;

    public ZetaWinningStrategy(WinningStrategy winningStrategyBeta, WinningStrategy winningStrategyEpsilon) {
        this.winningStrategyBeta = winningStrategyBeta;
        this.winningStrategyEpsilon = winningStrategyEpsilon;
    }

    @Override
    public Player findWinner(int age, HashMap<Position, CityImpl> cities) {
        if (age < -2000) {
            return winningStrategyBeta.findWinner(age, cities);
        } else {
            return winningStrategyEpsilon.findWinner(age, cities);
        }
    }

    @Override
    public void incrementNumberOfWonBattles(Player player) {
        winningStrategyEpsilon.incrementNumberOfWonBattles(player);
    }
}
