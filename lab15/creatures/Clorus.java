package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of the Clorus, a fierce blue-colored predator.
 *
 * @author Gabriel Chiong
 */
public class Clorus extends Creature {

    /**
     * Private static final variables.
     */
    private static final double MOVE_E = 0.03;
    private static final double STAY_E = 0.01;
    private static final double MIN_E = 0.0;

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * Creates a clorus with an energy of e.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Sets the color of the clorus.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * A clorus loses 0.03 energy to MOVE.
     */
    public void move() {
        energy -= MOVE_E;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * A clorus loses 0.01 energy to STAY.
     */
    public void stay() {
        energy -= STAY_E;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * A clorus gains another creature's energy when attacking.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus keeps 50% of energy and other 50% to offspring.
     *
     * @return another child Clorus
     */
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    /**
     * Defines behavioral rules (chooseAction).
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1: Stay if no empty neighbors.
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();

        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2: If Plips seen, attack random one.
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.add(key);
            }
        }

        if (!plipNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }

        // Rule 3: If clorus has energy >= 1.0, replicate to random empty square.
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 4: Clorus moves to random empty square.
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }

    /**
     * Private function to determine location of random neighbor.
     */
    private Direction randomEntry(Deque<Direction> plipNeighbors) {
        int backOrFront = (int) Math.round(Math.random());
        if (backOrFront == 0) {
            return plipNeighbors.getFirst();
        } else {
            return plipNeighbors.getLast();
        }
    }
}
