package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private Queue<Integer> fringe;  // FIFO or LILO.

    private static final int INFINITY = Integer.MAX_VALUE;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        edgeTo[s] = s;
        marked[s] = true;
        fringe = new ArrayDeque<>();
        fringe.add(s);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {

        /* Set all other vertex distances to infinity. */
        for (int v = 0; v < maze.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;

        while (!fringe.contains(t)) {
            int v = fringe.poll();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    fringe.add(w);
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

