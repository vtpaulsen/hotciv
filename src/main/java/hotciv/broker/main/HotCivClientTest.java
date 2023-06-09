package hotciv.broker.main;

import hotciv.broker.ClientRequestHandler;
import hotciv.broker.GameProxy;
import hotciv.broker.Requestor;
import hotciv.broker.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class HotCivClientTest {

    public static void main(String[] args) {
        new HotCivClientTest(args[0]);
    }

    private Game game;

    public HotCivClientTest(String hostname){
        ClientRequestHandler crh =  new SocketClientRequestHandler();
        crh.setServer(hostname, 37321);
        Requestor requestor = new StandardJSONRequestor(crh);
        this.game = new GameProxy(requestor);

        //minidraw
        DrawingEditor editor =
                new MiniDrawApplication( "Click top shield to end the turn",
                        new HotCivFactory4(game) );

        editor.setTool( new CompositionTool(editor, game) );

        editor.open();
    }

    private void test(Game game){
        //game.addObserver();
    }
}
