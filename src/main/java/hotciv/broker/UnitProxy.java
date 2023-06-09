package hotciv.broker;

import frds.broker.ClientProxy;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit, ClientProxy {

    public static String GAME_OBJECTID;

    private final Requestor requestor;

    public UnitProxy(String id, Requestor requestor){
        this.requestor=requestor;
        this.GAME_OBJECTID = id;
    }

    @Override
    public String getTypeString() {
        String returnedString = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.UNIT_GET_TYPE_STRING_OPERATION, String.class);
        return returnedString;
    }

    @Override
    public Player getOwner() {
        Player returnedOwner = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.UNIT_GET_OWNER_OPERATION, Player.class);
        return returnedOwner;
    }

    @Override
    public int getMoveCount() {
        int returnedMoveCount = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.UNIT_GET_MOVE_COUNT_OPERATION, int.class);
        return returnedMoveCount;
    }

    @Override
    public int getDefensiveStrength() {
        int returnedDefense = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.UNIT_GET_DEFENSIVE_STRENGTH_OPERATION, int.class);
        return returnedDefense;
    }

    @Override
    public int getAttackingStrength() {
        int returnedAttack = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.UNIT_GET_ATTACKING_STRENGTH_OPERATION, int.class);
        return returnedAttack;
    }

    @Override
    public String getId() {
        return GAME_OBJECTID;
    }
}
