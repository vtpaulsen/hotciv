package hotciv.broker;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.UUID;

public class StubCity implements City {

    private Player player;
    private int size;
    private String id;
    public StubCity(Player p, int size){
        this.player=p;
        this.size=size;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return Player.GREEN;
    }

    @Override
    public int getSize() {
        return 42;
    }

    @Override
    public int getTreasury() {
        return 31;
    }

    @Override
    public String getProduction() {
        return GameConstants.ARCHER;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setProduction(String s) {
    }

    public void changeOwner(Player player) {
    }


}
