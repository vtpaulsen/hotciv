package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.variants.deltaCiv.DeltaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaCiv {
    
    private GameImpl game;

    /**
     * Fixture for deltaCiv testing.
     */
    @BeforeEach
    public void setUp(){
        game = new GameImpl(new DeltaGameFactory());
    }

    @Test
    public void shouldBePlainsAt0_4() {
        assertThat(game.getTileAt(new Position(0, 4)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeOceanAt0_0() {
        assertThat(game.getTileAt(new Position(0, 0)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeForestAt5_5() {
        assertThat(game.getTileAt(new Position(5, 5)).getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldBePlainsAt8_8() {
        assertThat(game.getTileAt(new Position(8, 8)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeOceanAt15_0() {
        assertThat(game.getTileAt(new Position(15, 0)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeOceanAt15_15() {
        assertThat(game.getTileAt(new Position(15, 15)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeOceanAt0_15() {
        assertThat(game.getTileAt(new Position(0, 15)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBePlainsAt12_3() {
        assertThat(game.getTileAt(new Position(12, 3)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeMountainAt11_4() {
        assertThat(game.getTileAt(new Position(11, 4)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldBeBlueCityAt4_5() {
        assertThat(game.getCityAt(new Position(4, 5)), is(not(nullValue())));
    }

    @Test
    public void shouldBeRedCityAt8_12() {
        assertThat(game.getCityAt(new Position(8, 12)), is(not(nullValue())));
    }

    @Test
    public void shouldNotBeAnEmptyUnitMap(){
        assertThat(game.getUnitsMap().isEmpty(),is(false));
    }

}
