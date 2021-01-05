package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cycleFound;
    private int[] dummyEdgeTo;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        int startX = 1;
        int startY = 1;
        int s = maze.xyTo1D(startX, startY);
        marked[s] = true;
        dummyEdgeTo = new int[edgeTo.length];
        dummyEdgeTo[s] = s;
        distTo[s] = 0;
        cycleFound = false;
        dfs(s);
    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (marked[w] && dummyEdgeTo[v] != w) {
                cycleFound = true;
                return;
            }
            if (!marked[w]) {
                dummyEdgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                dfs(w);
                if (cycleFound) {
                    return;
                }
            }
        }
    }

    private void iterDFS(int v) {
        return;
    }
}

