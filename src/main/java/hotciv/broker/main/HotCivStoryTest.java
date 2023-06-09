package hotciv.broker.main;

import hotciv.broker.ClientRequestHandler;
import hotciv.broker.GameProxy;
import hotciv.broker.Requestor;
import hotciv.broker.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class HotCivStoryTest {

    public static void main(String[] args) {
        new HotCivStoryTest(args[0]);
    }

    private Game game;

    public HotCivStoryTest(String hostname){
        ClientRequestHandler crh =  new SocketClientRequestHandler();

        crh.setServer(hostname, 37321);

        Requestor requestor = new StandardJSONRequestor(crh);

        this.game = new GameProxy(requestor);
        testSimpleMethods(game);
    }

    private void testSimpleMethods(Game game){
        System.out.println("Testing simple methods");
        System.out.println("--> Game Age " + game.getAge());
        System.out.println("--> Game Winner " + game.getWinner());
        System.out.println("--> Game Player in turn " + game.getPlayerInTurn());
        System.out.println("--> Game moveUnit " + game.moveUnit(new Position(2,0), new Position(2,1)));

    }
}
