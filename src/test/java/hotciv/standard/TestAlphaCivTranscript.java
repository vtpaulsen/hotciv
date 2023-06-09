package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.alphaCiv.AlphaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAlphaCivTranscript {

    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new TranscriptDecorator(new GameImpl(new AlphaGameFactory()));
    }

    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldBeRedCityAt1_1() {
        Position p = new Position(1, 1);
        assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
    }
    @Test
    public void shouldBeOceanAt1_0() {
        Position p = new Position(1, 0);
        assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.OCEANS));
    }
    @Test
    public void unitsCannotMoveOverMountain() {
        game.moveUnit(new Position(2,0),new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(2,1), new Position(2,2)),is(false));
    }



    @Test
    public void cityPopulationSizeIsAlways1() {
        CityImpl c = new CityImpl(null);
        assertThat(c.getSize(), is(1));
    }

    @Test
    public void redWinsAtYear3000BC() {
        // The game starts at year 4000 BC
        assertThat(game.getAge(), is(-4000));
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
        }
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueInTurnAfterRed() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }



    @Test
    public void citiesShouldProduceSixProductionsAfterRoundEnd() {
        Position p1 = new Position(1, 1);
        assertThat(game.getCityAt(p1).getTreasury(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(p1).getTreasury(), is(7));
    }

    @Test
    public void shouldTake100Years() {
        // The game starts at 4000 BC
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        game.endOfTurn();
        // 1 round should take 100 years
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void blueHasACityAt4_1() {
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void redAttackAndKillsABlueUnit() {
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.endOfTurn(); game.endOfTurn();
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        assertThat(game.getUnitAt(new Position(3, 2)).getOwner(), is(Player.RED));
    }

    @Test
    public void citiesShouldProduceAUnit() {
        Position redCity = new Position(1, 1);
        assertThat(game.getUnitAt(redCity), is(nullValue()));
        game.endOfTurn(); game.endOfTurn();
        game.endOfTurn(); game.endOfTurn();
        assertThat(game.getUnitAt(redCity), is(not(nullValue())));
    }

    @Test
    public void citiesShouldCouldProduceMoreThan1Unit() {
        Position redCity = new Position(1, 1);
        assertThat(game.getUnitAt(redCity), is(nullValue()));
        game.endOfTurn(); game.endOfTurn();
        game.endOfTurn(); game.endOfTurn();
        assertThat(game.getUnitAt(redCity), is(not(nullValue())));
        game.endOfTurn(); game.endOfTurn();
        game.endOfTurn(); game.endOfTurn();
        assertThat(game.getUnitAt(new Position(0, 1)), is(notNullValue()));
    }

    @Test
    public void shouldRemoveTreasureWhenAUnitIsPlaced() {
        Position redCity = new Position(1, 1);
        assertThat(game.getUnitAt(redCity), is(nullValue()));
        // redCity has 1 treasure
        game.endOfTurn(); game.endOfTurn();
        game.endOfTurn(); game.endOfTurn();
        assertThat(game.getUnitAt(redCity), is(not(nullValue())));
        assertThat(game.getCityAt(redCity).getTreasury(), is(3));
    }

    @Test
    public void shouldReturnAUnitsClass() {
        // Checks that the position is not empty
        assertThat(game.getUnitAt(new Position(2, 0)), is(notNullValue()));
        // Checks that it is an archer
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));

    }

    @Test
    public void shouldNotBeAllowedToMoveFriendlyUnitsOnTopOfEachOther(){
        // red archer at 2,0 and red settler at 4,3
        game.moveUnit(new Position(2, 0),new Position(3,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3, 1),new Position(4, 2));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(4, 2),new Position(4, 3));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void shouldTakeOverACityIfRedMovesIntoABlueCity(){
        // Red has a city at 1,1
        // Blue has a legion at 3,2
        game.endOfTurn(); // to make it so blue can move his units
        game.moveUnit(new Position(3,2), new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1), new Position(1,1));
        assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void unitsCantMoveOutOfBounds(){
        //red has an archer at 2,0
        game.moveUnit(new Position(2,0), new Position(2, -1));
        assertThat(game.getUnitAt(new Position(2, -1)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2,0)), is(not(nullValue())));
    }

    @Test
    public void shouldOnlyAllowUnitsToMoveOncePerTurn(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(game.moveUnit(new Position(3,0),new Position(4,0)), is(false));

    }

    @Test
    public void shouldIncrementUnitsMoveCountWhenMoved(){
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), is(1));
        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(), is(0));
    }

    @Test
    public void shouldResetMoveCountAfterEndTurn(){
        game.moveUnit(new Position(2,0), new Position(3,0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(), is(1));
    }

}
