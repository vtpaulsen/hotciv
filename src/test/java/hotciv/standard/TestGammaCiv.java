package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.gammaCiv.GammaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaCiv {

    private Game game;

    /**
     * Fixture for gammaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new GammaGameFactory());
    }

    @Test
    public void shouldBuildCity() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getCityAt(new Position(4, 3)), is(not(nullValue())));
    }

    @Test
    public void shouldNotBuildCity() {
        game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getCityAt(new Position(2, 0)), is(nullValue()));
    }

    @Test
    public void shouldHaveTheSameOwnerAsSettler() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldDoubleStrengthWhenFortified() {
        Position pos = new Position(2, 0);
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(6));
    }

    @Test
    public void shouldReturn3DefenseIfArcher() {
        Position pos = new Position(2, 0);
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldNotBeAllowedToFortifyTwice() {
        // Archer is not fortified, defensive strength is = 3.
        game.performUnitActionAt(new Position(2, 0)); // defensiveStrength = 6
        game.performUnitActionAt(new Position(2, 0)); // defensiveStrength = 3
        // The defensive strength should be back at the value 3.
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldNotBeAllowedToMoveWhenFortified() {
        game.performUnitActionAt(new Position(2, 0)); // The archer is fortified.
        game.moveUnit(new Position(2, 0), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)), is(nullValue())); // The unit should not move.
    }


    @Test
    public void unitsShouldNotSpawnOutsideTheMapWhenCitiesAtBorder(){
        // red has a settler at 4,3
        game.moveUnit(new Position(4,3), new Position(3,3));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3,3), new Position(2,3));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,3), new Position(1,3));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(1,3), new Position(0,3));
        game.performUnitActionAt(new Position(0,3));
        game.endOfTurn();
        game.endOfTurn(); // 7 treasure
        game.endOfTurn();
        game.endOfTurn(); // 13 treasure, so it produces an archer at 0,3 and removes 10 treasure
        game.endOfTurn();
        game.endOfTurn(); // 9 treasure
        game.endOfTurn();
        game.endOfTurn(); // 15 treasure, so it produces an archer at 0,4 since 0,3 is occupied
        assertThat(game.getUnitAt(new Position(0, 4)), is(not(nullValue())));

    }
}
