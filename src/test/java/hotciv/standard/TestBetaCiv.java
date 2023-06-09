package hotciv.standard;

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

public class TestBetaCiv {

    private AgingStrategy agingStrategy;
    private WinningStrategy winningStrategy;

    /**
     * Fixture for betaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        agingStrategy = new RegressiveAgingStrategy();
        winningStrategy = new ConquerWinningStrategy();
    }

    @Test
    public void shouldAge100yearsBetween4000BC_And3900BC() {
        assertThat(agingStrategy.calculateAge(-4000), is(-3900));
    }

    @Test
    public void shouldGoFromYear1BC_ToYear1() {
        assertThat(agingStrategy.calculateAge(-1), is(1));
    }

    @Test
    public void shouldGoFromYear1ToYear50() {
        assertThat(agingStrategy.calculateAge(1), is(50));
    }

    @Test
    public void shouldGoFromYear50ToYear100() {
        assertThat(agingStrategy.calculateAge(50), is(100));
    }

    @Test
    public void shouldAge50yearsBetweenYear50AndYear1750() {
        assertThat(agingStrategy.calculateAge(1600), is(1650));
    }

    @Test
    public void shouldAge25YearsBetweenYear1750AndYear1900() {
        assertThat(agingStrategy.calculateAge(1800), is(1825));
    }

    @Test
    public void shouldAge5YearsBetweenYear1900AndYear1970() {
        assertThat(agingStrategy.calculateAge(1900), is(1905));
    }

    @Test
    public void shouldAge1YearBetweenYear1970AndLater() {
        assertThat(agingStrategy.calculateAge(1998), is(1999));
    }

    @Test
    public void nobodyShouldWinBeforeGameIsCreated() {
        assertThat(winningStrategy.findWinner(4000, new HashMap<>()), is(nullValue()));
    }

    @Test
    public void redShouldWinSinceMapOnlyContains1City() {
        HashMap<Position, CityImpl> cities = new HashMap<>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        assertThat(winningStrategy.findWinner(4000, cities), is(Player.RED));
    }

    @Test
    public void nobodyShouldWinSinceEachPlayerHasACity() {
        HashMap<Position, CityImpl> cities = new HashMap<>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(1, 2), new CityImpl(Player.BLUE));
        assertThat(winningStrategy.findWinner(4000, cities), is(nullValue()));
    }
}

