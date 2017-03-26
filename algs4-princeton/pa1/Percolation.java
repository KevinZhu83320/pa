import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.BitSet;

public class Percolation {
    private int n;
    private int max;
    private BitSet openFlags;
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF unionFind1;
    private int topSite;
    private int bottomSite;
    
   public Percolation(int n) {               // create n-by-n grid, with all sites blocked
       if (n <= 0) throw new java.lang.IllegalArgumentException("n should be great than zero");
       this.n = n;
       max = n *n;
       unionFind = new WeightedQuickUnionUF(max+2);
       unionFind1 = new WeightedQuickUnionUF(max+1);
       openFlags = new BitSet(max);
       topSite = max;
       bottomSite = max+1;
   }
   public void open(int row, int col) {    // open site (row, col) if it is not open already
       int idx = getIndexInUF(row, col);
       if (!isOpen(idx)) {
           openFlags.set(idx);
           if (row > 1 && isOpen(idx-n)) {
               unionFind.union(idx, idx-n);
               unionFind1.union(idx, idx-n);
           }
           if (row < n && isOpen(idx+n)) {
               unionFind.union(idx, idx+n);
               unionFind1.union(idx, idx+n);
           }
           if (col > 1 && isOpen(idx-1)) {
               unionFind.union(idx, idx-1);
               unionFind1.union(idx, idx-1);
           }
           if (col < n && isOpen(idx+1)) {
               unionFind.union(idx, idx+1);
               unionFind1.union(idx, idx+1);
           }
           if (row == 1) {
               unionFind.union(idx, topSite);
               unionFind1.union(idx, topSite);
           }
           if (row == n) {
               unionFind.union(idx, bottomSite);
           }
       }
   }
   public boolean isOpen(int row, int col) {  // is site (row, col) open
       return isOpen(getIndexInUF(row, col));
   }
   private boolean isOpen(int idx) {
       return openFlags.get(idx);
   }
   public boolean isFull(int row, int col) {  // is site (row, col) full?
       int idx = getIndexInUF(row, col);
       return unionFind1.connected(idx, topSite);
   }
   public int numberOfOpenSites() {       // number of open sites
       return openFlags.cardinality();
   }
   public boolean percolates() {              // does the system percolate?
       return unionFind.connected(topSite, bottomSite);
   }
   public static void main(String[] args) {   // test client (optional)
       
   }
   private int getIndexInUF(int row, int col) {
       if (row < 1 || row > n || col < 1 || col > n)
           throw new java.lang.IndexOutOfBoundsException("row or col is out of bound");
       return row * n + col - n - 1;
   }
}