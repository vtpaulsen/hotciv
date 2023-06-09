package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.main.NameService;
import hotciv.framework.City;
import hotciv.framework.Game;

import javax.servlet.http.HttpServletResponse;

public class CityInvoker implements Invoker {

    private final Gson gson;
    private final NameService nameService;
    private final Game servant;
    private String objectId;

    public CityInvoker(NameService nameService, Game servant) {
        this.gson = new Gson();
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

        City city = lookupCity();

        ReplyObject reply;

        if (operationName.equals(OperationNames.CITY_GET_OWNER_OPERATION)) {
            // No parameters.
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(city.getOwner().name()));

        } else if (operationName.equals(OperationNames.CITY_GET_SIZE_OPERATION)) {
            // No parameters.
            int age = city.getSize();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(age));

        } else if (operationName.equals(OperationNames.CITY_GET_TREASURY_OPERATION)) {
            // No parameters.
            int treasury = city.getTreasury();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(treasury));

        } else if (operationName.equals(OperationNames.CITY_GET_PRODUCTION_OPERATION)) {
            // No parameters.
            String production = city.getProduction();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(production));

        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED,
                    "This need to be a message.." + requestObject.getOperationName());
        }

        return gson.toJson(reply);
    }

    private City lookupCity() {
        return nameService.getCity(objectId);
    }
}
