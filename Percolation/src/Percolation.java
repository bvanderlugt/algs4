import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
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
    WeightedQuickUnionUF uf;

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
        uf = new WeightedQuickUnionUF(n);
    }

    /**
     * open site (row i, column j) if it is not open already
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        validate(xyTo1D(i, j));
        // define all of the neighbors
        int[] above = {i - 1, j};
        int[] right = {i, j + 1};
        int[] below = {i + 1, j};
        int[] left  = {i, j - 1};

        // put all the neighbors together, cause hey, they are neighbors
        int[][] neighbors = {above, right, below, left};

        // iterate through the neighbors checking for open sites to union with
        for (int[] k : neighbors) {
            if (isOpen(k[0], k[1])) {
                int k1d = xyTo1D(k[0], k[1]);
                int current1d = xyTo1D(i, j);
                uf.union(k1d, current1d);
            }
        }

    }

    /**
     * is site (row i, column j) open?
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        return grid[i][j] > -1;
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
        // TODO this looks bad, clean it up
        // iterate through the first row and test if the element connects with the input
        for (int p = 0; i < N; i++) {
            if ( uf.connected( xyTo1D(i, j), p ) ) { return true; }
        }
        return false;
    }

    /**
     * // does the system percolate?
     * @return
     */
    public boolean percolates() {
        int lastElement = N * N;
        int firstElemOfLastRow = lastElement - N;

        // test if an element in the last row connects with an element in the first
        for (int i = firstElemOfLastRow; i < lastElement + 1; i++){
            for (int p = 0; i < N; i++) {
                if (uf.connected(i, p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolate = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (percolate.isOpen(p, q)) continue;
            percolate.open(p, q);
            StdOut.println(p + " " + q);
        }
        if (percolate.percolates()) {
            System.out.println("I have percolated");
        }
    }
}
