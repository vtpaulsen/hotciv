package hotciv.standard;

import hotciv.broker.*;
import hotciv.framework.*;
import hotciv.variants.alphaCiv.AlphaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBrokerTwoGame {

    private Game game;
    private LocalMethodClientRequestHandler crh;

    @BeforeEach
    public void setup() {
        Game servant = new GameImpl(new AlphaGameFactory());
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new RootInvoker(servant);

        this.crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    public void testGetCityAt(){
        assertThat(game.getCityAt(new Position(1,1)).getId(), is(not(nullValue())));
        assertThat(game.getCityAt(new Position(1,1)).getId(),
                is(game.getCityAt(new Position(1,1)).getId()));
        assertThat(game.getCityAt(new Position(4,1)).getId()
                , is(not(game.getCityAt(new Position(1,1)))));
    }

    @Test
    public void testGetUnitAt(){
        // 2,0 og 3,2
        System.out.println(game.getUnitAt(new Position(14,14)));
        assertThat(game.getUnitAt(new Position(2,0)).getId(),
                is(game.getUnitAt(new Position(2,0)).getId()));
        assertThat(game.getUnitAt(new Position(2,0)).getId()
                , is(not(game.getUnitAt(new Position(3,2)).getId())));
        assertThat(game.getUnitAt(new Position(2,0)).getId()
                ,is(not(nullValue())));
    }

    @Test
    public void testGetWinner() {
        assertThat(game.getWinner(), is(nullValue()));
        for (int i = 0; i < 30; i++) {
            game.endOfTurn();
        }
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void testGetPlayerInTurn(){
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void testGetTileAt(){
        assertThat(game.getTileAt(new Position(2,2)).getTypeString(),is(GameConstants.MOUNTAINS));
    }

    @Test
    public void testGetTileAtID(){
        assertThat(game.getTileAt(new Position(1,1)).getId()
                , is(notNullValue()));
        assertThat(game.getTileAt(new Position(1,1)).getId()
                ,is(not(game.getTileAt(new Position(2,2)).getId())));
    }

    @Test
    public void testGetAge(){
        assertThat(game.getAge(), is(-4000));
    }

    //UNITS
    @Test
    public void testGetUnitAtMethods(){
        System.out.println(game.getUnitAt(new Position(2,0)));
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
    }
}
