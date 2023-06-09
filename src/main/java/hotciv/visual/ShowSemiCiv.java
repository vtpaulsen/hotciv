package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.FakeObjectGame;
import hotciv.variants.semiCiv.SemiGameFactory;
import hotciv.view.CivDrawing;
import hotciv.view.tool.CompositionTool;
import hotciv.view.tool.EndOfTurnTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiGameFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "Click top shield to end the turn",
                        new HotCivFactory4(game) );
        editor.open();

        editor.setTool( new CompositionTool(editor, game) );
    }
}
