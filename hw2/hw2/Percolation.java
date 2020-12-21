package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Corner Cases:
 * 1. row and column are indexed from 0 to N-1.
 * 2. (0, 0) refers to the upper left-hand side of grid.
 * 3. Throw exception if argument to open(), isOpen() or isFull() is invalid.
 * 4. Throw exception if N <= 0 argument for constructor.
 * <p>
 * Performance Requirements:
 * 1. Constructor time performance is N^2.
 * 2. All methods should take constant time.
 * 3. All methods should have constant number of calls to union(), find(),
 * connected(), and count().
 * 4. numberOfOpenSites() method must take constant time.
 * 5. Must use WeightedQuickUnionUF class.
 */
public class Percolation {

    // Global variables.
    private boolean[][] grid;
    private int openSites;
    private int dim;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    // Create N-by-N grid, with all sites initially blocked.
    public Percolation(int N) {
        validateConstructorInput(N);
        dim = N;
        grid = new boolean[N][N];
        openSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                grid[row][col] = false;
            }
        }
    }

    // Validate inputs to constructor
    private void validateConstructorInput(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid constructor input.");
        }
    }

    // Validate index to method calls.
    private void validateIndex(int row, int col) {
        if (row < 0 || col < 0 || row > dim - 1 || col > dim - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds of grid.");
        }
    }

    // Open the site (row, col) if it is not open already.
    public void open(int row, int col) {
        validateIndex(row, col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;

            // Check if top, left, right, bottom are open too?
            if (row - 1 >= 0) {
                if (isOpen(row - 1, col)) {
                    uf.union(gridToArray(row, col), gridToArray(row - 1, col));
                    uf2.union(gridToArray(row, col), gridToArray(row - 1, col));
                }
            }
            if (row + 1 < dim) {
                if (isOpen(row + 1, col)) {
                    uf.union(gridToArray(row, col), gridToArray(row + 1, col));
                    uf2.union(gridToArray(row, col), gridToArray(row + 1, col));
                }
            }
            if (col - 1 >= 0) {
                if (isOpen(row, col - 1)) {
                    uf.union(gridToArray(row, col), gridToArray(row, col - 1));
                    uf2.union(gridToArray(row, col), gridToArray(row, col - 1));
                }
            }
            if (col + 1 < dim) {
                if (isOpen(row, col + 1)) {
                    uf.union(gridToArray(row, col), gridToArray(row, col + 1));
                    uf2.union(gridToArray(row, col), gridToArray(row, col + 1));
                }
            }

            // Check if top or bottom row - connect to virtual site.
            if (row == 0) {
                uf.union(gridToArray(row, col), dim * dim);
                uf2.union(gridToArray(row, col), dim * dim);
            }
            if (row == dim - 1) {
                uf.union(gridToArray(row, col), dim * dim + 1);
            }
        }
    }

    // Helper method to convert from grid coords to array.
    private int gridToArray(int row, int col) {
        return row * dim + col;
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return grid[row][col];
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        return uf2.connected(gridToArray(row, col), dim * dim);
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return openSites;
    }

    // Does the system percolate?
    public boolean percolates() {
        return uf.connected(dim * dim, dim * dim + 1);
    }
}
