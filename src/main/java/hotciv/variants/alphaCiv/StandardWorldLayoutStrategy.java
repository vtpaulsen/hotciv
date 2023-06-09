package hotciv.variants.alphaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.WorldLayoutStrategy;

import java.util.HashMap;

public class StandardWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public HashMap[] worldLayout() {
        HashMap<Position, TileImpl> tiles = new HashMap<>();
        HashMap<Position, UnitImpl> units = new HashMap<>();
        HashMap<Position, CityImpl> cities = new HashMap<>();
        HashMap[] theMap = new HashMap[3];

        for (int i = 0; i <= GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j <= GameConstants.WORLDSIZE; j++) {
                tiles.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }

        tiles.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
        tiles.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
        tiles.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));

        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(4, 1), new CityImpl(Player.BLUE));

        units.put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER));
        units.put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        units.put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER));


        theMap[0] = tiles;
        theMap[1] = units;
        theMap[2] = cities;

        return theMap;
    }
}
