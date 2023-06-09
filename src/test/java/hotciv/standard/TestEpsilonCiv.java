package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.epsilonCiv.EpsilonUnitAttackStrategy;
import hotciv.variants.epsilonCiv.FixedEpsilonGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestEpsilonCiv {
    private GameImpl game;
    private UnitAttackStrategy as;

    /**
     * Fixture for epsilonCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new FixedEpsilonGameFactory());
        as = new EpsilonUnitAttackStrategy(new FixedDice());
    }

    @Test
    public void shouldReturnFalseSinceSameAttackAndDefense(){
        game.placeUnitAt(new Position(8,8),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,9),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,10),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,11),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(7,8),new UnitImpl(Player.BLUE,GameConstants.LEGION));
        game.placeUnitAt(new Position(7,9),new UnitImpl(Player.BLUE,GameConstants.LEGION));
        game.placeUnitAt(new Position(7,10),new UnitImpl(Player.BLUE,GameConstants.LEGION));
        game.placeUnitAt(new Position(7,11),new UnitImpl(Player.BLUE,GameConstants.LEGION));
        assertThat(as.attack(game,new Position(8,8),new Position(7,8)), is(false));
    }

    @Test
    public void redWinsTheBattle(){
        game.placeUnitAt(new Position(8,8),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,9),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,10),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,11),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(7,8),new UnitImpl(Player.BLUE,GameConstants.LEGION));
        assertThat(as.attack(game,new Position(8,8),new Position(7,8)), is(true));
    }

    @Test
    public void redWinsByAttacks(){
        // year 1900
        for (int i = 0; i < 42; i++) {
            game.endOfTurn();
        }
        game.endOfTurn(); // now blues turn

        game.placeUnitAt(new Position(8,8),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,9),new UnitImpl(Player.BLUE,GameConstants.LEGION));

        game.placeUnitAt(new Position(11,11),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(11,12),new UnitImpl(Player.BLUE,GameConstants.LEGION));

        game.placeUnitAt(new Position(14,14),new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(14,15),new UnitImpl(Player.BLUE,GameConstants.LEGION));

        game.moveUnit(new Position(8, 9), new Position(8, 8));
        game.moveUnit(new Position(11, 12), new Position(11, 11));
        game.moveUnit(new Position(14, 15), new Position(14, 14));

        assertThat(game.getWinner(), is(Player.BLUE));
    }

}
