package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.variants.alphaCiv.AlphaGameFactory;
import hotciv.variants.semiCiv.SemiGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Observer;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestObserverPattern {
    private GameImpl game;
    private GameObserverSpy gameObserver;

    @BeforeEach
    public void setUp(){
        game = new GameImpl(new AlphaGameFactory());
        gameObserver = new GameObserverSpy();
        game.addObserver(gameObserver);
    }

    @Test
    public void testMove(){
        // Red archer at 2,0
        game.moveUnit(new Position(2,0), new Position(2,1));
        assertThat(gameObserver.getLastCalledMethod(), is("worldChangedAt"));
    }

    @Test
    public void testTurnEnds(){
        game.endOfTurn();
        assertThat(gameObserver.getLastCalledMethod(), is("turnEnds"));
    }

    @Test
    public void testTurnEnds2(){
        game.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        assertThat(gameObserver.getLastCalledMethod(), is("turnEnds"));
    }

}
