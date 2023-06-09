package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.IPCException;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.main.NameService;
import hotciv.framework.*;

import javax.servlet.http.HttpServletResponse;

public class GameInvoker implements Invoker {

    private final Game servant;
    private final Gson gson;
    private final NameService nameService;


    public GameInvoker(NameService nameService, Game servant) {
        this.servant = servant;
        this.gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

        /* As there is only ONE HotCiv instance (a singleton)
           the objectId is not used for anything in our case. */
        try {
            // Dispatching on all known operations
            // Each dispatch follows the same algorithm
            // a) retrieve parameters from json array (if any)
            // b) invoke servant method
            // c) populate a reply object with return values

            if (requestObject.getOperationName().equals(OperationNames.GAME_ADD_OBSERVER_OPERATION)) {
                // Parameter convention: [0] = GameObserver
                GameObserver gameObserver = gson.fromJson(array.get(0), GameObserver.class);

                servant.addObserver(gameObserver);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_AGE_OPERATION)) {
                // No parameters.
                int age = servant.getAge();

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(age));

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER_OPERATION)) {
                // No parameters.
                Player winner = servant.getWinner();

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(winner));

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION)) {
                // Parameter convention: [0] = Position from
                Position from = gson.fromJson(array.get(0), Position.class);
                // Parameter convention: [1] = Position to
                String unitType = gson.fromJson(array.get(1), String.class);

                servant.changeProductionInCityAt(from, unitType);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_MOVE_UNIT_OPERATION)) {
                // Parameter convention: [0] = Position from
                Position from = gson.fromJson(array.get(0), Position.class);
                // Parameter convention: [1] = Position to
                Position to = gson.fromJson(array.get(1), Position.class);

                boolean legalMove = servant.moveUnit(from, to);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(legalMove));

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN_OPERATION)) {
                // No parameters.
                servant.endOfTurn();

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_PERFORM_UNIT_ACTION_AT_OPERATION)) {
                // Parameter convention: [0] = Position on
                Position unitAt = gson.fromJson(array.get(0), Position.class);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(unitAt));

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_CITY_AT_OPERATION)) {
                // Parameter convention: [0] = Position on
                Position cityAt = gson.fromJson(array.get(0), Position.class);
                City city = servant.getCityAt(cityAt);
                String id = "";
                if (city != null){
                    id = city.getId();
                    nameService.putCity(city.getId(), city);
                    reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
                } else {
                    reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);
                }

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TILE_AT_OPERATION)) {
                // Parameter convention: [0] = Position from
                Position tileAt = gson.fromJson(array.get(0), Position.class);
                Tile tile = servant.getTileAt(tileAt);
                String id = tile.getId();
                nameService.putTile(id, tile);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
            }else if (requestObject.getOperationName().equals(OperationNames.GAME_SET_TILE_FOCUS_OPERATION)) {
                // Parameter convention: [0] = Position
                Position pos = gson.fromJson(array.get(0), Position.class);
                servant.setTileFocus(pos);

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_UNIT_AT_OPERATION)) {
                // Parameter convention: [0] = Position onTile
                Position unitAt = gson.fromJson(array.get(0), Position.class);
                Unit unit = servant.getUnitAt(unitAt);
                String id = "";
                if (unit != null){
                    nameService.putUnit(unit.getId(), unit);
                    id = unit.getId();
                    reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
                } else {
                    reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);
                }

            } else if (requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAYER_IN_TURN_OPERATION)) {
                // No parameters.
                Player player = servant.getPlayerInTurn();

                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(player));

            } else {
                reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
                        "Implementation missing for operation named: " + requestObject.getOperationName());
            }

        } catch (IPCException i) {
            reply = new ReplyObject(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, i.getMessage());
        }

        return gson.toJson(reply);
    }

}
