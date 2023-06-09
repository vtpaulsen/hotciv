package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.variants.thetaCiv.ThetaGameConstants;

import java.util.UUID;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String unitClass;
    private int defensiveStrenght;
    private int attackingStrenght;
    private Boolean fortified;
    private int moveCount;
    private String id;

    public UnitImpl(Player p, String unitClass) {
        this.owner = p;
        this.unitClass = unitClass;
        this.fortified = false;
        if (unitClass.equals(ThetaGameConstants.SANDWORM)){
            this.moveCount = 2;
        } else {
            this.moveCount = 1;
        }

        if (unitClass == GameConstants.ARCHER){
            this.defensiveStrenght = 3;
            this.attackingStrenght = 2;
        } else if(unitClass == GameConstants.LEGION){
            this.defensiveStrenght = 2;
            this.attackingStrenght = 4;
        } else if(unitClass == GameConstants.SETTLER){
            this.defensiveStrenght = 3;
            this.attackingStrenght = 0;
        }
        this.id = UUID.randomUUID().toString();

    }

    @Override
    public String getTypeString() {
        return unitClass;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    public void incrementMoveCount() {
        moveCount = moveCount - 1;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrenght;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrenght;
    }

    @Override
    public String getId() {
        return id;
    }

    public void fortify() {
        if (fortified) {
            defensiveStrenght = defensiveStrenght / 2;
            fortified = false;
        } else {
            defensiveStrenght = defensiveStrenght * 2;
            fortified = true;
        }
    }

    public Boolean isFortified() {
        return fortified;
    }

    public void resetMoveCountToZero(){
        if (unitClass.equals(ThetaGameConstants.SANDWORM)){
            moveCount = 2;
        } else
        {
        moveCount = 1;
        }
    }
}
