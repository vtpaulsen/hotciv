package hotciv.broker;

import com.google.gson.Gson;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.main.NameService;
import hotciv.framework.Game;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class RootInvoker implements Invoker {

    private final HashMap<String, Invoker> invokerMap;
    private final Gson gson;
    private final Game servant;
    private final NameService nameService;

    public RootInvoker(Game servant) {
        this.invokerMap = new HashMap<>();
        this.gson = new Gson();
        this.servant = servant;
        this.nameService = new NameService();

        invokerMap.put(OperationNames.GAME_PREFIX, new GameInvoker(nameService, servant));
        invokerMap.put(OperationNames.CITY_PREFIX, new CityInvoker(nameService, servant));
        invokerMap.put(OperationNames.UNIT_PREFIX, new UnitInvoker(nameService, servant));
        invokerMap.put(OperationNames.TILE_PREFIX, new TileInvoker(nameService, servant));
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        String reply;

        // Identify the invoker to use
        String type = operationName.substring(0, operationName.indexOf(OperationNames.SEPERATOR));
        Invoker subInvoker = invokerMap.get(type);

        // Do the up-call on the subInvoker
        try {
            reply = subInvoker.handleRequest(request);
        } catch (UnknownError e) {
            reply = gson.toJson(
                    new ReplyObject(
                            HttpServletResponse.SC_NOT_FOUND,
                            e.getMessage()));
        }

        return reply;
    }
}
