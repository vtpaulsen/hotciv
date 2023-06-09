package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Tile;

import java.util.UUID;

public class TileImpl implements Tile {

    private String tileType;
    private String id;

    public TileImpl(String type) {
        this.tileType = type;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return tileType;
    }

    @Override
    public String getId() {
        return id;
    }


}
