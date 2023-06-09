package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.UUID;

public class CityImpl implements City {


    private int treasure = 1;
    private Player player;
    private String unitProduction;
    private String id;

    public CityImpl(Player p) {
        this.player = p;
        this.unitProduction = GameConstants.ARCHER;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getTreasury() {
        return treasure;
    }

    @Override
    public String getProduction() {
        return unitProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    public void addTreasure() {
        this.treasure = treasure + 6;
    }



    public void removeTreasure() {
        if (unitProduction == GameConstants.ARCHER) {
            this.treasure = treasure - 10;
        } else if (unitProduction.equals(GameConstants.LEGION)) {
            this.treasure = treasure - 15;
        } else if (unitProduction.equals(GameConstants.SETTLER)){
            this.treasure = treasure - 30;
        }
    }

    public void setProduction(String s) {
        this.unitProduction = s;
    }


    public void changeOwner(Player p){
        this.player = p;
    }
}
