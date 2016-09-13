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
    private int UFLength;
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
        UFLength =  ( n + 1 ) * ( n + 1);
        uf = new WeightedQuickUnionUF(UFLength);
    }

    /**
     * open site (row i, column j) if it is not open already
     * @param i
     * @param j
     */
    public void open(int i, int j) {
//        validate(i);
//        validate(j);
        xyTo1D(i, j);
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
//        validate(i);
//        validate(j);
        int p = xyTo1D(i, j);
        return uf.find(p) == p;
    }

    /**
     * validate that input is within bounds of N
     * @param p
     */
    private void validate(int p) {
        if (p < 1 || p > N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + N);
        }
    }

    /**
     * convert a point in a 2d grid to an index of a 1d array
     * @param p
     * @param q
     * @return
     */
    private int xyTo1D(int p, int q) {
        int converted = (p * N - 1)  + q;
        System.out.println("in xyto1d \n...converted is: " + converted);
        return converted;
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
        int firstElemOfLastRow = UFLength - N;
        // test if an element in the last row connects with an element in the first
        for (int i = firstElemOfLastRow; i < UFLength + 1; i++){
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
        System.out.println("input n is: " + n);
        Percolation percolate = new Percolation(n);
        System.out.println("isopen is: " + percolate.isOpen(4,2));
//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
////            System.out.println("p is: " + p);
//            int q = StdIn.readInt();
////            System.out.println("q is: " + q);
////            if (percolate.isOpen(p, q)) continue;
//            percolate.open(p, q);
//            System.out.println("is the coord full?" + percolate.isFull(p, q));
//            System.out.println("does the system percolate?" + percolate.percolates());
//            System.out.println("now is the system full?" + percolate.isFull(p, q));
//            StdOut.println(p + " " + q);
//        }
    }
}
