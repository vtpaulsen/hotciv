package hotciv.broker;

import frds.broker.ClientProxy;
import hotciv.framework.*;

public class CityProxy implements City, ClientProxy {

    /* As there is only ONE HotCiv game servant, the
    objectId is really not sed in the HotCiv case, so
    we just provide a 'dummy' string.
     */
    public static String CITY_OBJECTID;

    private final Requestor requestor;

    public CityProxy(String id, Requestor requestor) {
        this.requestor = requestor;
        this.CITY_OBJECTID = id;
    }

    @Override
    public Player getOwner() {
        Player returnedPlayer = requestor.sendRequestAndAwaitReply(CITY_OBJECTID,
                OperationNames.CITY_GET_OWNER_OPERATION, Player.class);
        return returnedPlayer;
    }

    @Override
    public int getSize() {
        int returnedSize = requestor.sendRequestAndAwaitReply(CITY_OBJECTID,
                OperationNames.CITY_GET_SIZE_OPERATION, int.class);
        return returnedSize;
    }

    @Override
    public int getTreasury() {
        int returnedTreasury = requestor.sendRequestAndAwaitReply(CITY_OBJECTID,
                OperationNames.CITY_GET_TREASURY_OPERATION, int.class);
        return returnedTreasury;
    }

    @Override
    public String getProduction() {
        String returnedProduction = requestor.sendRequestAndAwaitReply(CITY_OBJECTID,
                OperationNames.CITY_GET_PRODUCTION_OPERATION, String.class);
        return returnedProduction;
    }

    @Override
    public String getWorkforceFocus() {
        String returnedWorkforce = requestor.sendRequestAndAwaitReply(CITY_OBJECTID,
                OperationNames.CITY_GET_WORKFORCE_FOCUS_OPERATION, String.class);
        return returnedWorkforce;
    }

    @Override
    public String getId() {
        return CITY_OBJECTID;
    }
}
