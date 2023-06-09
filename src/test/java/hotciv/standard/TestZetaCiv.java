package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.zetaCiv.ZetaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaCiv {
    private GameImpl game;

    /**
     * Fixture for zetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ZetaGameFactory());
    }

    @Test
    public void shouldLetBlueWinAtYear2100BCbyConquer() {
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getWinner(), is(nullValue()));
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldNotLetBlueWinAtYear1600BCbyConquer() {
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        for (int i = 0; i < 42; i++) {
            game.endOfTurn();
        }
        // Year 1900BC so there should only be a winner if someone wins 3 battles
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldLetRedWinAtYear1000BCbyBattlesWon() {
        for (int i = 0; i < 42; i++) {
            game.endOfTurn();
        }
        game.placeUnitAt(new Position(8, 8), new UnitImpl(Player.RED, GameConstants.LEGION));
        game.placeUnitAt(new Position(8, 8), new UnitImpl(Player.BLUE, GameConstants.SETTLER));
    }

    @Test
    public void totalDestructionBattle() {
        for (int i = 0; i < 300; i++) {
            game.placeUnitAt(new Position(7, 7), new UnitImpl(Player.RED, GameConstants.LEGION));
            game.placeUnitAt(new Position(6, 7), new UnitImpl(Player.BLUE, GameConstants.ARCHER));
            game.moveUnit(new Position(7, 7), new Position(6, 7));
            game.removeUnitAt(new Position(7, 7));
            game.removeUnitAt(new Position(6, 7));
            game.endOfTurn();
            game.endOfTurn();

        }
        assertThat(game.getWinner(), is(Player.RED));
    }

}
