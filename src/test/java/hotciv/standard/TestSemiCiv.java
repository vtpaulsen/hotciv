package hotciv.standard;

import hotciv.variants.deltaCiv.DeltaGameFactory;
import hotciv.variants.semiCiv.SemiGameFactory;
import org.junit.jupiter.api.BeforeEach;

public class TestSemiCiv {
    private GameImpl game;

    /**
     * Fixture for deltaCiv testing.
     */
    @BeforeEach
    public void setUp(){
        game = new GameImpl(new SemiGameFactory());
    }

}
