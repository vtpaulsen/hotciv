package hotciv.broker;

import frds.broker.ClientProxy;
import hotciv.framework.Tile;

public class TileProxy implements Tile, ClientProxy {

    public static String GAME_OBJECTID;

    private final Requestor requestor;

    public TileProxy(String id, Requestor requestor) {
        this.requestor = requestor;
        this.GAME_OBJECTID = id;
    }

    @Override
    public String getTypeString() {
        String returnedString = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.TILE_GET_TYPE_STRING, String.class);
        return returnedString;

    }

    @Override
    public String getId() {
        return GAME_OBJECTID;
    }
}
