import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {
    private static Random r = new Random(500);

    @Test
    /** Test for Naive Point Set. */
    public void testNaivePointSet() {
        Point p11 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p21 = new Point(3.3, 4.4);
        Point p31 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p11, p21, p31));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(ret.getX(), 3.3, 0.01); // evaluates to 3.3
        assertEquals(ret.getY(), 4.4, 0.01); // evaluates to 4.4
    }

    /** Test constructor and insert functionality against lecture slides example. */
    private static KdTree testConstructorAdd() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        return new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }

    @Test
    /** Test nearest function code by using examples from the nearest slides. */
    public void testNearestDemoSlides() {
        KdTree kd = testConstructorAdd();
        Point actual = kd.nearest(0, 7);
        Point actual2 = kd.nearest(0, 1000);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
        assertEquals(expected, actual2);

        Point actual3 = kd.nearest(0, 2);
        Point expected3 = new Point(2, 3);
        assertEquals(expected3, actual3);
    }

    @Test
    /** Randomized testing as per project specifications. */
    public void testWith1000Points() {
        List<Point> points1000 = randomPoints(1000);
        NaivePointSet nps = new NaivePointSet(points1000);
        KdTree kd = new KdTree(points1000);

        List<Point> queries200 = randomPoints(200);
        for (Point p : queries200) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    /** Return N random points. */
    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }

    /** Return single random point. */
    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    /** Runs the NaivePointSet method for time recording. */
    private void timingNaive(List<Point> points, List<Point> queries) {
        NaivePointSet nps = new NaivePointSet(points);
        for (Point p : queries) {
            Point nearest = nps.nearest(p.getX(), p.getY());
        }
    }

    /** Runs the KdTree method for time recording. */
    private void timingKd(List<Point> points, List<Point> queries) {
        KdTree kd = new KdTree(points);
        for (Point p : queries) {
            Point nearest = kd.nearest(p.getX(), p.getY());
        }
    }

    @Test
    /** Timing test between KdTree and NaivePointSet. */
    public void testTiming() {
        int pointsCount = 100000;
        int queriesCount = 10000;

        List<Point> points = randomPoints(pointsCount);
        List<Point> queries = randomPoints(queriesCount);

        /* NaivePointSet time. */
        long npsStart = System.currentTimeMillis();
        timingNaive(points, queries);
        long npsEnd = System.currentTimeMillis();
        long npsTime = npsEnd - npsStart;

        /* KdTree time. */
        long kdStart = System.currentTimeMillis();
        timingKd(points, queries);
        long kdEnd = System.currentTimeMillis();
        long kdTime = kdEnd - kdStart;

        System.out.println("Timing for NPS: " + npsTime);
        System.out.println("Timing for KdTree: " + kdTime);
    }
}
