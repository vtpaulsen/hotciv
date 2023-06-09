package hotciv.broker.main;

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;

public class NameService {
    private HashMap<Object, Tile> namingServiceTile;
    private HashMap<String, City> namingServiceCity;
    private HashMap<String, Unit> namingServiceUnit;

    public NameService(){
        this.namingServiceUnit = new HashMap<>();
        this.namingServiceCity = new HashMap<>();
        this.namingServiceTile = new HashMap<>();
    }

    public City getCity(String id){
        return namingServiceCity.get(id);
    }

    public void putCity(String id, City city){
        namingServiceCity.put(id, city);
    }

    public Unit getUnit(String id){
        return namingServiceUnit.get(id);
    }

    public void putUnit(String id, Unit unit){
        namingServiceUnit.put(id, unit);
    }

    public void putTile(String id, Tile tile){
        namingServiceTile.put(id, tile);
    }

    public Tile getTile(String objectId) {
       return namingServiceTile.get(objectId);
    }
}
