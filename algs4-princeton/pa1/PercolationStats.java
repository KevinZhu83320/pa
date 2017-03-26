import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
    
public class PercolationStats {
    private double[] xs;
    private int trials;
    
    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException("n or trails should be greater than 0");
        this.trials = trials;
        xs = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            // StdRandom.setSeed(System.currentTimeMillis());
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            xs[i] = p.numberOfOpenSites() * 1.00 / n / n;
        }
    }
    public double mean()    {                      // sample mean of percolation threshold
        return StdStats.mean(xs);
    }
    public double stddev() {                        // sample standard deviation of percolation threshold
        return StdStats.stddev(xs);
    }
    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return StdStats.mean(xs) - 1.96 * StdStats.stddev(xs) / Math.sqrt(trials + 0.0);
    }
    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        return StdStats.mean(xs) + 1.96 * StdStats.stddev(xs) / Math.sqrt(trials + 0.0);
    }
    public static void main(String[] args) {        // test client (described below)
        if (null != args && args.length == 2) {
            int n = Integer.parseInt(args[0]);
            int t = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, t);
            System.out.println("mean\t=" + ps.mean());
            System.out.println("stddev\t=" + ps.stddev());
            System.out.println("95% confidence interval \t=[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
        }
    }
}