package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.WorldLayoutStrategy;
import hotciv.variants.thetaCiv.ThetaGameConstants;
import hotciv.variants.thetaCiv.ThetaUnitImpl;

import java.util.*;

public class ThetaWorldLayoutStrategy implements WorldLayoutStrategy {
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
        units.put(new Position(9, 6), new UnitImpl(Player.BLUE, ThetaGameConstants.SANDWORM));

        String[] layout = new String[]{
                "...oododdoo.....",
                "..ohhdddddddddd.",
                ".oddddMddd...dd.",
                ".odMMddododdddoo",
                "...ofodddddddo..",
                ".ofddddoddddddo.",
                "...odd..dddddd..",
                ".oddddddddddoM..",
                ".oddddddddddddf.",
                "offddddoddddddoo",
                "oodddodo...oddoo",
                ".ooMMdddd...ddd.",
                "..oodddddfoddd..",
                "....oddddMMdo...",
                "..ooddddddMd....",
                ".....oddddddoo..",
        };
        HashMap<Position, Tile> theWorld = new HashMap<Position, Tile>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                if (tileChar == 'd') {
                    type = ThetaGameConstants.DESERT;
                }

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