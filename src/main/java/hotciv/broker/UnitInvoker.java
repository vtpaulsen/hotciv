package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.main.NameService;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import javax.servlet.http.HttpServletResponse;

public class UnitInvoker implements Invoker {
    private Gson gson;
    private final NameService nameService;
    private final Game servant;
    private String objectId;

    public UnitInvoker(NameService nameService, Game servant) {
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

        Unit unit = lookupUnit(objectId);

        ReplyObject reply;

        if (operationName.equals(OperationNames.UNIT_GET_OWNER_OPERATION)) {
            // No parameters.
            Player owner = unit.getOwner();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(owner));

        } else if (operationName.equals(OperationNames.UNIT_GET_ATTACKING_STRENGTH_OPERATION)) {
            // No parameters.
            int strength = unit.getAttackingStrength();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(strength));

        } else if (operationName.equals(OperationNames.UNIT_GET_DEFENSIVE_STRENGTH_OPERATION)) {
            // No parameters.
            int defense = unit.getDefensiveStrength();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(defense));

        } else if (operationName.equals(OperationNames.UNIT_GET_MOVE_COUNT_OPERATION)) {
            // No parameters.
            int moveCount = unit.getMoveCount();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(moveCount));

        } else if (operationName.equals(OperationNames.UNIT_GET_TYPE_STRING_OPERATION)) {
            // No parameters.
            String type = unit.getTypeString();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(type));

        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
                    "This need to be a message.." + requestObject.getOperationName());
        }

        return gson.toJson(reply);
    }

    private Unit lookupUnit(String objectId) {
        return nameService.getUnit(objectId);
    }
}
