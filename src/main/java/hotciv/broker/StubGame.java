package hotciv.broker;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;
import hotciv.stub.StubTile;

public class StubGame implements Game, Servant {

    private Position position_of_green_city = new Position(1,1);
    private Position position_of_tile = new Position(1,1);
    private Position position_of_unit = new Position(1,1);
    private Unit unit = new UnitImpl(Player.GREEN, GameConstants.ARCHER);



    @Override
    public Tile getTileAt(Position p) {
        if (p.equals(position_of_tile)) {
            return new StubTile(GameConstants.PLAINS);
        }
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        if (p.equals(position_of_unit)){
            return new StubUnit();
        }
        return null;
    }



    @Override
    public City getCityAt(Position p) {
        if (p.equals(position_of_green_city)){
            return new StubCity(Player.GREEN, 7);
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return Player.YELLOW;
    }

    @Override
    public Player getWinner() {
        return Player.GREEN;
    }

    @Override
    public int getAge() {
        return 12832;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return true;
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    @Override
    public String getId() {
        return null;
    }
}
