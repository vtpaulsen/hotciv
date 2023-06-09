package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Position mouseClick = GfxConstants.getPositionFromXY(x, y);
        if (game.getUnitAt(mouseClick) != null
                || game.getCityAt(mouseClick) != null) {
            game.setTileFocus(GfxConstants.getPositionFromXY(x, y));
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
