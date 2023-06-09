package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import javax.lang.model.SourceVersion;
import javax.tools.Tool;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class UnitMoveTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position mouseDown;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        HotCivFigure figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
        if (figureBelowClickPoint != null) {
            if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)) {
                mouseDown = GfxConstants.getPositionFromXY(x, y);
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
        if (!mouseDown.equals(GfxConstants.getPositionFromXY(x, y))) {
            if (game.moveUnit(mouseDown, GfxConstants.getPositionFromXY(x, y))) {
               // game.moveUnit(mouseDown, GfxConstants.getPositionFromXY(x, y));
            }
        }
    }

    public void activate() {
    }

    public void deactivate() {
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
    }
}
