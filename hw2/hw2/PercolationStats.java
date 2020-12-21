package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Steps:
 * 1. Initialize all sites to be blocked.
 * 2. Repeat the following until the system percolates.
 * - Choose site randomly from all blocked sites.
 * - Open the site.
 * - Fraction of open sites when system percolates is the threshold.
 * <p>
 * Performance Requirements:
 * 1. Constructor throws exception if either N <= 0 or T <= 0.
 * 2. Use PercolationFactory object pf to create new Percolation objects.
 * 3. NEVER call new Percolation(N) in PercolationStats.
 * 4. Use edu.princeton.cs.introcs.StdRandom to generate random numbers.
 */
public class PercolationStats {

    // Declare global variables.
    private double[] fracs;
    private int trials;

    // Perform T independent experiments on an N-by-N grid.
    public PercolationStats(int N, int T, PercolationFactory pf) {
        validateConstructorInputs(N, T);

        trials = T;
        fracs = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            fracs[i] = p.numberOfOpenSites() / (double) (N * N);
        }
    }

    // Validate the inputs into the constructor.
    private void validateConstructorInputs(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Invalid constructor inputs.");
        }
    }

    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(fracs);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(fracs);
    }

    // Low endpoint of 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    // High endpoint of 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }
}
