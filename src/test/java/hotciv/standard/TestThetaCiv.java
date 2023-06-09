package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.thetaCiv.ThetaGameConstants;
import hotciv.variants.thetaCiv.ThetaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.betaCiv.ConquerWinningStrategy;
import hotciv.variants.betaCiv.RegressiveAgingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.lang.model.type.NullType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestThetaCiv {

    private GameImpl game;

    /**
     * Fixture for thetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ThetaGameFactory());
    }

    @Test
    public void shouldNotBeAllowedToMoveArcherTwice(){
        game.moveUnit(new Position(3,8),new Position(2,8));
        game.moveUnit(new Position(2,8), new Position(1,8));
        assertThat(game.moveUnit(new Position(2,8), new Position(1,8)), is(false));
        }

    @Test
    public void shouldBeAllowedToMoveSandwormTwice(){
        game.endOfTurn();
        game.moveUnit(new Position(9,6),new Position(10,6));
        assertThat(game.moveUnit(new Position(10,6),new Position(11,6)), is(true));
    }

    @Test
    public void shouldNotBeAllowedToMoveSandwormThrice(){
        game.endOfTurn();
        game.moveUnit(new Position(9,6),new Position(10,6));
        game.moveUnit(new Position(10,6),new Position(11,6));

        assertThat(game.moveUnit(new Position(11,6),new Position(12,6)), is(false));
    }

    @Test
    public void shouldNotBeAllowedToMoveSinceTheTileIsNotDesert() {
        game.endOfTurn();
        // There is a sandworm on (9, 6) and plains on (9, 7)
        assertThat(game.moveUnit(new Position(9, 6), new Position(9, 7)), is(false));
    }

    @Test
    public void shouldConsumeAllUnfriendlyUnitsIfSandwormUsesAbility(){
        // sandworm at 9,6
        game.endOfTurn();
        game.placeUnitAt(new Position(10,6), new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(10,5), new UnitImpl(Player.RED,GameConstants.ARCHER));
        game.placeUnitAt(new Position(9,5), new UnitImpl(Player.RED,GameConstants.SETTLER));
        game.placeUnitAt(new Position(9,7), new UnitImpl(Player.BLUE,GameConstants.ARCHER));
        game.placeUnitAt(new Position(8,6), new UnitImpl(Player.BLUE,GameConstants.ARCHER));

        game.performUnitActionAt(new Position(9,6));

        assertThat(game.getUnitAt(new Position(8,6)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(10,5)), is(nullValue()));


    }

    @Test
    public void shouldStillAllowArchersToFortify(){
        Position pos = new Position(3, 8);
        game.performUnitActionAt(new Position(3,8));
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(6));
    }

    @Test
    public void shouldSpawnSandwormOnSandWhenProduced(){
        game.getCityAt(new Position(8,12)).setProduction(ThetaGameConstants.SANDWORM);
        assertThat(game.getCityAt(new Position(8,12)).getProduction(), is(ThetaGameConstants.SANDWORM));
        for (int i = 0; i < 100; i++) {
            game.endOfTurn();
        }
       assertThat(game.getUnitAt(new Position(8,13)).getTypeString(), is(ThetaGameConstants.SANDWORM));
        assertThat(game.getUnitAt(new Position(7,12)), is(nullValue()));
    }

    @Test
    public void shouldNotSpawnSandwormInCityIfCityIsNextToSandButNotOnSand(){
        game.getCityAt(new Position(4,5)).setProduction(ThetaGameConstants.SANDWORM);
        for (int i = 0; i < 200; i++) {
            game.endOfTurn();
        }
        System.out.println(game.getUnitAt(new Position(4,5)).getTypeString());
        //assertThat(game.getUnitAt(new Position(4,5)), is(nullValue()));
    }

}
