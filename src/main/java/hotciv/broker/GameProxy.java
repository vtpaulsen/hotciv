package hotciv.broker;

import frds.broker.ClientProxy;
import hotciv.framework.*;

import java.util.ArrayList;

public class GameProxy implements Game, ClientProxy {

    /* As there is only ONE HotCiv game servant, the
    objectId is really not sed in the HotCiv case, so
    we just provide a 'dummy' string.
     */
    public static final String GAME_OBJECTID = "singleton";

    private final Requestor requestor;
    private ArrayList<GameObserver> gameObservers;


    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
        this.gameObservers = new ArrayList<>();

    }

    @Override
    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_GET_UNIT_AT_OPERATION, String.class, p);

        if (id != null){
            return new UnitProxy(id, requestor);
        }
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_GET_CITY_AT_OPERATION, String.class, p);

        if (id != null){
            return new CityProxy(id, requestor);
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        Player playerInTurn = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_GET_PLAYER_IN_TURN_OPERATION, Player.class);
        return playerInTurn;
    }

    @Override
    public Player getWinner() {
        Player winner =
                requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                        OperationNames.GAME_GET_WINNER_OPERATION,
                        Player.class);
        return winner;
    }

    @Override
    public Tile getTileAt(Position position) {
        String id = requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_GET_TILE_AT_OPERATION, String.class, position);
        TileProxy tp = new TileProxy(id, requestor);

        return tp;
    }

    @Override
    public int getAge() {
        int age =
                requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                        OperationNames.GAME_GET_AGE_OPERATION,
                        int.class);
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Boolean returnedBoolean =
                requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                        OperationNames.GAME_MOVE_UNIT_OPERATION,
                        boolean.class, from, to);
        if (returnedBoolean){
            for (GameObserver obs : gameObservers) {
                obs.worldChangedAt(from);
                obs.worldChangedAt(to);
            }
        }
        return returnedBoolean;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_END_OF_TURN_OPERATION,
                void.class);
        for (GameObserver obs : gameObservers) {
            obs.turnEnds(getPlayerInTurn(), getAge());
        }
    }

    @Override
    public void changeWorkforceFocusInCityAt(Position position, String balance) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_OPERATION,
                void.class, position, balance);
    }

    @Override
    public void changeProductionInCityAt(Position position, String unitType) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION,
                void.class, position, unitType);
    }

    @Override
    public void performUnitActionAt(Position position) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_PERFORM_UNIT_ACTION_AT_OPERATION,
                void.class, position);
        for (GameObserver obs : gameObservers) {
            obs.worldChangedAt(position);
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        gameObservers.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        requestor.sendRequestAndAwaitReply(GAME_OBJECTID,
                OperationNames.GAME_SET_TILE_FOCUS_OPERATION,
                void.class, position);
        for (GameObserver obs : gameObservers) {
            obs.tileFocusChangedAt(position);
        }
    }

    @Override
    public String getId() {
        return null;
    }
}
