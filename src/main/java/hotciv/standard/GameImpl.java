package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {

    private ArrayList<GameObserver> gameObservers;
    private HashMap<Position, TileImpl> tiles;
    private HashMap<Position, UnitImpl> units;
    private HashMap<Position, CityImpl> cities;

    private Player playerInTurn = Player.RED;
    private int age = -4000;     // The game starts at year 4000 BC
    private int noOfRounds = 0;

    private final AgingStrategy agingStrategy;
    private final WinningStrategy winningStrategy;
    private final UnitActionsStrategy unitActions;
    private final WorldLayoutStrategy worldLayout;
    private final UnitAttackStrategy unitAttackStrategy;
    private final MoveCountsStrategy moveCountsStrategy;
    private final CheckIfMoveIsValidStrategy checkIfMoveIsValidStrategy;
    private final UnitProductionStrategy unitProductionStrategy;
    private final UnitSpawnCheckStrategy unitSpawnCheckStrategy;

    public GameImpl(GameFactory gameFactory) {
        this.agingStrategy = gameFactory.createAgingStrategy();
        this.winningStrategy = gameFactory.createWinningStrategy();
        this.unitActions = gameFactory.createUnitActionsStrategy();
        this.worldLayout = gameFactory.createWorldLayout();
        this.unitAttackStrategy = gameFactory.createUnitAttackStrategy();
        this.moveCountsStrategy = gameFactory.createMoveCountsStrategy();
        this.checkIfMoveIsValidStrategy = gameFactory.createCheckIfMoveIsValidStrategy();
        this.unitProductionStrategy = gameFactory.createUnitProductionStrategy();
        this.unitSpawnCheckStrategy = gameFactory.createUnitSpawnCheckStrategy();
        this.gameObservers = new ArrayList<>();

        HashMap[] tilesUnitsAndCities = worldLayout.worldLayout();

        tiles = tilesUnitsAndCities[0];
        units = tilesUnitsAndCities[1];
        cities = tilesUnitsAndCities[2];
    }

    public TileImpl getTileAt(Position p) {
        return tiles.get(p);
    }

    public UnitImpl getUnitAt(Position p) {
        return units.get(p);
    }

    public CityImpl getCityAt(Position p) {
        return cities.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winningStrategy.findWinner(age, cities);
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {

        boolean thereAreMoveCountsAvailable = moveCountsStrategy.moveCountsLeft(getUnitAt(from));
        if (!thereAreMoveCountsAvailable) return false;

        boolean thereShouldBeAUnitAtFrom = getUnitAt(from) != null;
        if (!thereShouldBeAUnitAtFrom) return false;

        boolean theOwnerOfTheUnitShouldBeThePlayerInTurn = getUnitAt(from).getOwner().equals(getPlayerInTurn());
        if (!theOwnerOfTheUnitShouldBeThePlayerInTurn) return false;

        boolean archerIsFortified = getUnitAt(from).isFortified();
        if (archerIsFortified) return false;

        boolean theUnitMoveIsValid = checkIfTheMoveIsValid(from, to);
        if (!theUnitMoveIsValid) return false;

        conquerACity(from, to);

        if (unitAttackStrategy.attack(this, from, to)) {
            winningStrategy.incrementNumberOfWonBattles(getUnitAt(from).getOwner());
            placeUnitAt(to, getUnitAt(from));
            removeUnitAt(from);
        }

        getUnitAt(to).incrementMoveCount();
        for (GameObserver obs : gameObservers) {
            setTileFocus(to);
            obs.worldChangedAt(from);
            obs.worldChangedAt(to);
        }
        return true;
    }

    private void conquerACity(Position from, Position to) {
        if (getCityAt(to) != null
                && getUnitAt(from).getOwner() != getCityAt(to).getOwner()) {
            getCityAt(to).changeOwner(getUnitAt(from).getOwner());
        }
    }

    public void removeUnitAt(Position from) {
        units.remove(from);
    }

    private void addTreasureToAllCities() {
        for (CityImpl c : cities.values()) {
            c.addTreasure();
        }
    }

    private void produceUnits() {
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                Position position = new Position(i, j);
                if (getCityAt(position) != null && getCityAt(position).getTreasury() >= unitProductionStrategy.getProductionCost(getCityAt(position).getProduction())) {
                    if (getUnitAt(position) == null) {
                        placeUnitAt(position, new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(position);
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i - 1, j), position)) {
                        placeUnitAt(new Position(i - 1, j), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i - 1, j));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i - 1, j + 1), position)) {
                        placeUnitAt(new Position(i - 1, j + 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i - 1, j + 1));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i, j + 1), position)) {
                        placeUnitAt(new Position(i, j + 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i, j + 1));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i + 1, j + 1), position)) {
                        placeUnitAt(new Position(i + 1, j + 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i + 1, j + 1));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i + 1, j), position)) {
                        placeUnitAt(new Position(i + 1, j), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i + 1, j));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i + 1, j - 1), position)) {
                        placeUnitAt(new Position(i + 1, j - 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i + 1, j - 1));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i, j - 1), position)) {
                        placeUnitAt(new Position(i, j - 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i, j - 1));
                        }
                    } else if (unitSpawnCheckStrategy.checkIfValidUnitPosition(this, new Position(i - 1, j - 1), position)) {
                        placeUnitAt(new Position(i - 1, j - 1), new UnitImpl(getCityAt(position).getOwner(), getCityAt(position).getProduction()));
                        getCityAt(position).removeTreasure();
                        for (GameObserver obs : gameObservers) {
                            obs.worldChangedAt(new Position(i - 1, j - 1));
                        }
                    }
                }
            }
        }
    }

    public void endOfTurn() {
        if (getPlayerInTurn() == Player.RED) {
            playerInTurn = Player.BLUE;
        } else {
            playerInTurn = Player.RED;
            addTreasureToAllCities();
            produceUnits();
            resetAllMoveCounts();
            setAge();
            incrementNoOfRounds();
        }
        for (GameObserver obs : gameObservers) {
            obs.turnEnds(getPlayerInTurn(), getAge());
        }
    }

    private void setAge() {
        age = agingStrategy.calculateAge(age);
    }

    private void resetAllMoveCounts() {
        for (UnitImpl u : units.values()) {
            u.resetMoveCountToZero();
        }
    }

    public void changeWorkforceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        cities.get(p).setProduction(unitType);
    }

    public void performUnitActionAt(Position pos) {
        unitActions.unitAction(this, pos);
        for (GameObserver obs : gameObservers) {
            obs.worldChangedAt(pos);
        }
    }

    public void placeUnitAt(Position p, UnitImpl u) {
        units.put(p, u);
    }


    // Used for moving units with moveUnit()
    private boolean checkIfTheMoveIsValid(Position from, Position to) {
        return checkIfMoveIsValidStrategy.checkIfTheMoveIsValid(this, from, to);
    }

    public boolean checkIfOutOfBounds(Position to) {
        return checkIfMoveIsValidStrategy.checkIfOutOfBounds(to);
    }

    public HashMap getCitiesMap() {
        return cities;
    }

    public Map getUnitsMap() {
        return units;
    }

    public int getNoOfRounds() {
        return noOfRounds;
    }

    private void incrementNoOfRounds() {
        this.noOfRounds += 1;
    }

    @Override
    public void addObserver(GameObserver observer) {
        this.gameObservers.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        for (GameObserver obs : gameObservers) {
            obs.tileFocusChangedAt(position);
        }

    }

    @Override
    public String getId() {
        return null;
    }
}














