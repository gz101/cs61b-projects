package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int distance;
        private SearchNode prev;
        private int priority;

        public SearchNode(WorldState st, int d, SearchNode p) {
            state = st;
            distance = d;
            prev = p;
            priority = st.estimatedDistanceToGoal() + distance;
        }

        public WorldState whatState() {
            return state;
        }

        public int whatDistance() {
            return distance;
        }

        public SearchNode parent() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    private MinPQ<SearchNode> bms;   // MinPQ data structure for BFS.
    private List<WorldState> seq;
    private int qCount;

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        bms = new MinPQ<>();
        seq = new ArrayList<>();
        SearchNode init = new SearchNode(initial, 0, null);
        bms.insert(init);
        qCount = 1;
        bfs();
    }

    /** Helper method to run bfs. */
    private void bfs() {
        SearchNode minNode = bms.min();

        while (!minNode.whatState().isGoal()) {
            for (WorldState w : minNode.whatState().neighbors()) {
                if (minNode.parent() == null || !w.equals(minNode.parent().whatState())) {
                    SearchNode s = new SearchNode(w, minNode.whatDistance() + 1, minNode);
                    bms.insert(s);
                    qCount++;
                }
            }
            minNode = bms.delMin();
        }
        for (SearchNode ptr = minNode; ptr != null; ptr = ptr.parent()) {
            seq.add(ptr.whatState());
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return seq.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        Collections.reverse(seq);
        return seq;
    }

    /** Returns the number of items queued into MinPQ. */
    /** private int queueCount() {
        return qCount;
    } */
}
