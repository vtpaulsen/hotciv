package hotciv.standard;

import hotciv.broker.*;
import hotciv.framework.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBrokerOneGame {

    private Game game;
    private Unit unit;
    private LocalMethodClientRequestHandler crh;

    @BeforeEach
    public void setup() {
        Game servant = new StubGame();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new RootInvoker(servant);

        this.crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    // Test Game.
    @Test
    public void testGetAge() {
        int age = game.getAge();
        assertThat(age, is(12832));
    }

    @Test
    public void testMoveUnit() {
        boolean lars = game.moveUnit(new Position(1, 1), new Position(2, 2));
        assertThat(lars, is(true));
    }

    @Test
    public void testchangeProductionInCityAt() {
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
        System.out.println(crh.getLastReply());
        assertThat(crh.getLastRequest(), containsString("gameImpl_change-production-in-city-at"));
    }

    @Test
    public void shouldHaveWinner() {
        Player winner = game.getWinner();
        assertThat(winner, is(Player.GREEN));
        assertThat(crh.getLastRequest(), containsString("gameImpl_get-winner"));
    }


    @Test
    public void testGetTileAt() {
        game.getTileAt(new Position(1, 1));
        assertThat(crh.getLastRequest(), containsString("gameImpl_get-tile-at"));
    }


    @Test
    public void testGetPlayerInTurn() {
        Player player = game.getPlayerInTurn();
        assertThat(player, is(Player.YELLOW));
    }

    @Test
    public void testGetCityAt(){
        System.out.println(game.getCityAt(new Position(1,1)).getId());
        System.out.println(game.getCityAt(new Position(1,1)).getId());

    }




}
