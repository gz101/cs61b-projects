import java.util.ArrayList;
import java.util.List;

/**
 * Project 2B (2019): K-d Trees
 * CS61B UC Berkeley
 * @author Gabriel Chiong
 */
public class NaivePointSet implements PointSet {
    private List<Point> set;

    /** Constructor. Assume points has at least size 1. */
    public NaivePointSet(List<Point> points) {
        set = new ArrayList<>();
        for (Point p : points) {
            set.add(p);
        }
    }

    @Override
    /**
     * Returns the closest point to the inputted coordinates.
     * Takes O(N) time, where N is the number of points.
     */
    public Point nearest(double x, double y) {
        Point virtual = new Point(x, y);
        Point best = set.get(0);
        for (Point p : set) {
            double currentDistance = Point.distance(p, virtual);
            if (currentDistance < Point.distance(best, virtual)) {
                best = p;
            }
        }
        return best;
    }
}
