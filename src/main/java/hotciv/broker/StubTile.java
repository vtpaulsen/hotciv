package hotciv.broker;

import hotciv.framework.Tile;

public class StubTile implements Tile {
    private String type;
    public StubTile(String type) {
        this.type = type;
    }
    public String getTypeString() { return type; }

    @Override
    public String getId() {
        return null;
    }
}