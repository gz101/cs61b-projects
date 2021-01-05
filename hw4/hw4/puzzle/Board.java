package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] board;
    private int[][] goal;
    private final int BLANK = 0;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j.
     */
    public Board(int[][] tiles) {

        board = new int[tiles.length][tiles.length];
        goal = new int[tiles.length][tiles.length];

        /* Make board immutable. */
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                board[i][j] = tiles[i][j];
            }
        }

        /* Initialize goal board. */
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                goal[i][j] = (i * size()) + (j + 1);
            }
        }
        goal[size() - 1][size() - 1] = BLANK;
    }

    /** Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= size() || j < 0 || j >= size()) {
            throw new IndexOutOfBoundsException("Invalid i and j values.");
        }
        return board[i][j];
    }

    /** Returns the board size N. */
    public int size() {
        return board.length;
    }

    @Override
    /**
     * Returns the neighbors of the current board.
     * @author SPOILERZ (Cited from http://joshh.ug/neighbors.html)
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate described below. */
    public int hamming() {
        int hamCount = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != BLANK && tileAt(i, j) != goal[i][j]) {
                    hamCount++;
                }
            }
        }
        return hamCount;
    }

    /** Manhattan estimate described below. */
    public int manhattan() {
        int manCount = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != BLANK && tileAt(i, j) != goal[i][j]) {
                    int manDist = manHelp(i, j);
                    manCount += manDist;
                }
            }
        }
        return manCount;
    }

    private int manHelp(int i, int j) {
        int correctI = 0;
        int correctJ = 0;
        for (int c = 0; c < size(); c++) {
            for (int r = 0; r < size(); r++) {
                if (goal[c][r] == tileAt(i, j)) {
                    correctI = c;
                    correctJ = r;
                    break;
                }
            }
        }
        int distI = Math.abs(correctI - i);
        int distJ = Math.abs(correctJ - j);
        return distI + distJ;
    }

    @Override
    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * Gradescope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's.
     */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (y != null || y.getClass() == getClass()) {
            Board other = (Board) y;

            if (other.size() != size()) {
                return false;
            }

            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < size(); j++) {
                    if (tileAt(i, j) != other.tileAt(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the string representation of the board.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
