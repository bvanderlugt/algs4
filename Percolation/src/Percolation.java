/**
 * Created by blair on 9/4/16.
 */

public class Percolation {

    private int[][] grid;

    /**
     * create n-by-n grid, with all sites blocked
     * blocked sites are indicated by int 0
     * @param n
     */
    public Percolation(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than zero");
        }
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
