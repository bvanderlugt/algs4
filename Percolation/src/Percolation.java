import edu.princeton.cs.algs4.StdIn;
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
        // Java fills arrays with zero by default
        grid = new int[n + 1][n + 1];
        UFLength =  ( n + 1 ) * ( n + 1);
        uf = new WeightedQuickUnionUF(n);
    }

    /**
     * open site (row i, column j) if it is not open already
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        validateGrid(i);
        validateGrid(j);
//        xyTo1D(i, j);
        // define all of the neighbors
        int[] above = {i - 1, j};
        int[] right = {i, j + 1};
        int[] below = {i + 1, j};
        int[] left  = {i, j - 1};

        // put all the neighbors together, cause hey, they are neighbors
        int[][] neighbors = {above, right, below, left};

        // iterate through the neighbors checking for open sites to union with
        // TODO skip neighbors if the index does not exist
        for (int[] neighbor : neighbors) {
            // returns true if grid element is not zero
            try {
                if (!isOpen(neighbor[0], neighbor[1])) {
                    // if the site is not open then open it and join to neighbors
                    int neighbor1d = xyTo1D(neighbor[0], neighbor[1]);
                    int current1d = xyTo1D(i, j);
                    uf.union(neighbor1d, current1d);
                }
            } catch (Exception IndexOutOfBoundsException) {
                System.out.println("neighbor not found in open, moving on...");
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
        validateGrid(i);
        validateGrid(j);
//        int p = xyTo1D(i, j);
        System.out.println("in isOpen; grid[i][j] is: " + grid[i][j]);
        return grid[i][j] != 0;
    }

    /**
     * validateGrid that input is within bounds of N
     * @param p
     */
    private void validateGrid(int p) {
        if (p < 1 || p > N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + N);
        }
    }

    /**
     * convert a point in a 2d grid to an index of a 1d array
     * @param p 1 based index of grid
     * @param q 1 based index of grid
     * @return
     */
    private int xyTo1D(int p, int q) {
        // convert p and q to a zero based index
        int pZeroBased = p - 1;
        int qZeroBased = q - 1;
        int converted = (pZeroBased * N)  + qZeroBased;
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
        int firstElemOfLastRow = (N * N) - N;
        // test if an element in the last row connects with an element in the first
        for (int i = firstElemOfLastRow; i < N + 1; i++){
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
        System.out.println("isopen is: " + percolate.isOpen(1,1));
        percolate.open(1, 1);
        System.out.println("isopen is: " + percolate.isOpen(1,1));
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
