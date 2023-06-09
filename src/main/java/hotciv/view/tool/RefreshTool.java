package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class RefreshTool extends NullTool {

    private final DrawingEditor editor;
    private final Game game;

    public RefreshTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y){
        super.mouseUp(e, x, y);
        editor.drawing().requestUpdate();
    }
}

