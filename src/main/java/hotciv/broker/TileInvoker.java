package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.main.NameService;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import javax.servlet.http.HttpServletResponse;

public class TileInvoker implements Invoker {
    private Gson gson;
    private final NameService nameService;
    private final Game servant;
    private String objectId;

    public TileInvoker(NameService nameService, Game servant) {
        gson = new Gson();
        this.nameService = nameService;
        this.servant = servant;
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        objectId = requestObject.getObjectId();
        String operationName = requestObject.getOperationName();
        String payload = requestObject.getPayload();

        JsonParser parser = new JsonParser();
        JsonArray array =
                parser.parse(payload).getAsJsonArray();

        Tile tile = lookupTile();

        ReplyObject reply;

        if (operationName.equals(OperationNames.TILE_GET_TYPE_STRING)) {
            // No parameters.
            String tileTypeString = tile.getTypeString();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(tileTypeString));

        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
                    "This need to be a message.." + requestObject.getOperationName());
        }

        return gson.toJson(reply);
    }

    private Tile lookupTile() {
        return nameService.getTile(objectId);
    }
}
