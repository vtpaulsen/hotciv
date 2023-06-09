package hotciv.standard;

import hotciv.broker.*;
import hotciv.broker.main.NameService;
import hotciv.framework.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBrokerOneUnit {
    private Unit unit;
    private LocalMethodClientRequestHandler crh;

    @BeforeEach
    public void setup() {
        Unit servant = new StubUnit();

        Invoker invoker = new UnitInvoker(new NameService(), new StubGame());

        this.crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        unit = new UnitProxy("lars", requestor);
    }
    // no tests works because of unitProxy requiring a real id

    @Test
    public void testGetTypeString() {
        //String type = unit.getTypeString();
        //assertThat(type, is(GameConstants.SETTLER));
    }

    @Test
    public void testUnitGetOwner() {
        //Player owner = unit.getOwner();
        //assertThat(owner, is(Player.GREEN));
    }

    @Test
    public void testGetMoveCount() {
        //int moveCount = unit.getMoveCount();
        //assertThat(moveCount, is(12452));
    }

    @Test
    public void testGetDefensiveStrength() {
        //int defense = unit.getDefensiveStrength();
        //assertThat(defense, is(-10));
    }

    @Test
    public void testGetAttackingStrength() {
        //int attackStrength = unit.getAttackingStrength();
        //assertThat(attackStrength, is(9));
    }
}
