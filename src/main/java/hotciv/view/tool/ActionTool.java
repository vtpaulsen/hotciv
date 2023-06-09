package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public ActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Position pos = GfxConstants.getPositionFromXY(x,y);
        if (e.isShiftDown()){
            if (game.getUnitAt(pos) != null){
               if (game.getUnitAt(pos).getOwner() == game.getPlayerInTurn()){
                   game.performUnitActionAt(pos);
               }
            }
        }

    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {

    }

    public void activate() { }

    public void deactivate() { }

    @Override
    public void keyDown(KeyEvent evt, int key) {
    }
}
