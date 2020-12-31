import java.util.List;

/**
 * Project 2B (2019): K-d Trees
 * CS61B UC Berkeley
 * @author Gabriel Chiong
 */
public class KdTree implements PointSet {
    private static final boolean HORIZONTAL = false;    // Vertical is True.

    private Node root;  // Root node of tree.

    private class Node {
        /* Coordinates stored in node. */
        private Point point;
        private boolean vertical;

        /* Children of this node. */
        private Node left;  // Also the down child.
        private Node right; // Also the up child.

        /* Node constructor. */
        private Node(Point point, boolean orientation) {
            this.point = point;
            this.vertical = orientation;
        }
    }

    /** Constructor. Assume points has at least size 1. */
    public KdTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    /** Private add method to add points to Kd Tree. */
    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.point)) {
            return n;
        }

        int cmp = comparePoints(p, n.point, orientation);
        if (cmp < 0) {
            n.left = add(p, n.left, !orientation);
        } else if (cmp >= 0) {
            n.right = add(p, n.right, !orientation);
        }
        return n;
    }

    /** Private comparison method to compare points in the correct orientation. */
    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    /**
     * Returns the closest point to the inputted coordinates.
     * Takes O(N) time, where N is the number of points.
     */
    public Point nearest(double x, double y) {
        Point p = new Point(x, y);
        return nearest(root, p, root).point;
    }

    /**
     * Private helper method to find nearest point.
     * Checks "good" side first to increase efficiency.
     * With pruning to optimize.
     */
    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }

        /* Split into goodSide and badSide nodes. */
        Node goodSide;
        Node badSide;
        if (Point.distance(goal, new Point(0, 0)) < Point.distance(n.point, new Point(0, 0))) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);

        /* Pruning step. */
        Point bestBadSidePoint;
        if (n.vertical == HORIZONTAL) {
            bestBadSidePoint = new Point(n.point.getX(), goal.getY());
        } else {
            bestBadSidePoint = new Point(goal.getX(), n.point.getY());
        }
        if (Point.distance(bestBadSidePoint, goal) < Point.distance(best.point, goal)) {
            best = nearest(badSide, goal, best);
        }

        return best;
    }

    /** Inefficient nearest (checks "good" side first to increase efficiency, but no pruning). */
    private Node slightlyInefficientNearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        Node goodSide;
        Node badSide;
        if (Point.distance(goal, new Point(0, 0)) < Point.distance(n.point, new Point(0, 0))) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);
        best = nearest(badSide, goal, best);
        return best;
    }

    /** Inefficient nearest (without checking "good" side first and pruning). */
    private Node mostInefficientNearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        best = nearest(n.left, goal, best);
        best = nearest(n.right, goal, best);
        return best;
    }
}
