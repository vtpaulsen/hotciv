package hotciv.variants.deltaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.*;



import java.util.HashMap;

public class CustomWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public HashMap[] worldLayout() {

        HashMap<Position, UnitImpl> units = new HashMap<>();
        HashMap<Position, CityImpl> cities = new HashMap<>();
        HashMap[] theMap = new HashMap[3];

        cities.put(new Position(4, 5), new CityImpl(Player.BLUE));
        cities.put(new Position(8, 12), new CityImpl(Player.RED));

        units.put(new Position(3, 8), new UnitImpl(Player.RED, GameConstants.ARCHER));
        units.put(new Position(5, 5), new UnitImpl(Player.RED, GameConstants.SETTLER));
        units.put(new Position(4, 4), new UnitImpl(Player.BLUE, GameConstants.LEGION));

        // Define the world as the DeltaCiv layout
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        HashMap<Position, Tile> theWorld = new HashMap<Position, Tile>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r, c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        theMap[0] = theWorld;
        theMap[1] = units;
        theMap[2] = cities;

        return theMap;
    }
}




