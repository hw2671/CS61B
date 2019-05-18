package creatures;

import huglife.Occupant;
import huglife.Creature;
import huglife.Empty;
import huglife.Direction;
import huglife.Action;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    public Clorus(double e) {
        super("clorus");
        this.energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus dup = new Clorus(this.energy / 2.0);
        this.energy = this.energy / 2.0;
        return dup;
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> seenPlips = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> neighbor : neighbors.entrySet()) {
            if (neighbor.getValue() instanceof Empty) {
                emptyNeighbors.add(neighbor.getKey());
            } else if (neighbor.getValue() instanceof Plip) {
                seenPlips.add(neighbor.getKey());
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!seenPlips.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomlyChoose(seenPlips));
        } else if (this.energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomlyChoose(emptyNeighbors));
        }
        return new Action(Action.ActionType.MOVE, randomlyChoose(emptyNeighbors));
    }

    private Direction randomlyChoose(Deque<Direction> plipNeighbors) {
        int rand = (int) (Math.random() * plipNeighbors.size()) + 1;
        if (rand == 1) {
            return plipNeighbors.removeFirst();
        } else if (rand == 2) {
            plipNeighbors.removeFirst();
            return plipNeighbors.removeFirst();
        } else if (rand == 3) {
            plipNeighbors.removeFirst();
            plipNeighbors.removeFirst();
            return plipNeighbors.removeFirst();
        } else {
            plipNeighbors.removeFirst();
            plipNeighbors.removeFirst();
            plipNeighbors.removeFirst();
            return plipNeighbors.removeFirst();
        }
    }
}
