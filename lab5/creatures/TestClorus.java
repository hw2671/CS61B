package creatures;
import huglife.Action;
import huglife.Occupant;
import huglife.Empty;
import huglife.Impassible;
import huglife.Direction;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {
    private Clorus newClorus = new Clorus(5.0);

    @Test
    public void testClorusName() {
        assertEquals("clorus", newClorus.name());
        assertNotEquals("Clorus", newClorus.name());
    }

    @Test
    public void testClorusLossesOrGainsEnergy() {
        double newEnergy = newClorus.energy() - 0.03;
        newClorus.move();
        assertEquals(newEnergy, newClorus.energy(), 0.03);

        newEnergy = newClorus.energy() - 0.01;
        newClorus.stay();
        assertEquals(newEnergy - 0.01, newClorus.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus one = new Clorus(1.2);
        Clorus two = one.replicate();
        assertNotEquals(one, two);
        assertEquals(one.energy(), two.energy(), two.energy());
    }

    @Test
    public void testAttack() {
        Clorus one = new Clorus(1.2);
        newClorus.attack(one);

        assertEquals(7.2, newClorus.energy(), 1.2);
    }

    @Test
    public void testActions() {
        // No empty adjacent spaces. Should stay.
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = newClorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(actual, expected);

        // if seen Plips, attack! (Plips at top and bottom directions
        HashMap<Direction, Occupant> seenPlips = new HashMap<>();
        seenPlips.put(Direction.TOP, new Plip());
        seenPlips.put(Direction.BOTTOM, new Impassible());
        seenPlips.put(Direction.LEFT, new Impassible());
        seenPlips.put(Direction.RIGHT, new Empty());

        actual = newClorus.chooseAction(seenPlips);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(actual, expected);

        // test replicate if energy >= 1 && empty spaces are present
        HashMap<Direction, Occupant> repliatingSpaces = new HashMap<>();
        repliatingSpaces.put(Direction.TOP, new Impassible());
        repliatingSpaces.put(Direction.BOTTOM, new Impassible());
        repliatingSpaces.put(Direction.LEFT, new Empty());
        repliatingSpaces.put(Direction.RIGHT, new Impassible());

        actual = newClorus.chooseAction(repliatingSpaces);
        expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);

        assertEquals(actual, expected);

        // if all else fails, move.
        Clorus testClorus = new Clorus(0.8);
        HashMap<Direction, Occupant> moveSpaces = new HashMap<>();
        moveSpaces.put(Direction.TOP, new Impassible());
        moveSpaces.put(Direction.BOTTOM, new Empty());
        moveSpaces.put(Direction.LEFT, new Impassible());
        moveSpaces.put(Direction.RIGHT, new Impassible());

        actual = testClorus.chooseAction(moveSpaces);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);

        assertEquals(actual, expected);

    }
}
