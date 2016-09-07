import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Blair Vanderlugt on 9/4/16.
 * Program to solve the percolation threshold using Monte Carlo simulation
 * Run the program by redirecting standard input from a file
 * java Percolation input10.txt
 */


public class Percolation {

    private int[][] grid;
    private int N;

    /**
     * create n-by-n grid, with all sites blocked
     * blocked sites are indicated by int 0
     * @param n
     */
    public Percolation(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("input must be greater than zero");
        }
        N = n;
        grid = new int[n][n];
    }

    /**
     * open site (row i, column j) if it is not open already
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        grid[i][j] = 1;

    }

    /**
     * is site (row i, column j) open?
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        return grid[i][j] == 1;
    }

    /**
     * validate that input is within bounds of N
     * @param p
     */
    private void validate(int p) {
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
        }
    }

    /**
     * convert a point in a 2d grid to an index of a 1d array
     * @param p
     * @param q
     * @return
     */
    private int xyTo1D(int p, int q) {
        return (N * p) + q;
    }

    /**
     * is site (row i, column j) full?
     * A full site is an open site that can be connected to an open site in the top
     * row via a chain of neighboring (left, right, up, down) open sites
     * e.g. can the liquid flow to this site from the top?
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
        return pass;
    }

    /**
     * // does the system percolate?
     * @return
     */
    public boolean percolates() {
        return pass;
    }

    public static void main(String[] args) {

    }
}
