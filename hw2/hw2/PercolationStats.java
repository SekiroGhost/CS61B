package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;


    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0) throw new java.lang.IllegalArgumentException("N should be larger than 0!");
        if (T <= 0) throw new java.lang.IllegalArgumentException("T should be larger than 0!");
        double[] results = new double[T];
        for (int i = 0; i < T; i ++){
            Percolation testPercolation = pf.make(N);
            while (!testPercolation.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                testPercolation.open(row, col);
            }
            results[i] = testPercolation.numberOfOpenSites();
            results[i] /= N * N;
        }

        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        this.confidenceLow = mean - (1.96 * stddev / Math.sqrt(T));
        this.confidenceHigh = mean + (1.96 * stddev / Math.sqrt(T));
    }
    

    public double mean(){
        return mean;
    }

    public double stddev(){
        return stddev;
    }

    public double confidenceHigh(){
        return confidenceHigh;
    }

    public double confidenceLow(){
        return confidenceLow;
    }


    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20, 1000, pf);

        System.out.println("The mean is " + test.mean());
        System.out.println("The stdev is " + test.stddev());
        System.out.println("The low conf is " + test.confidenceLow());
        System.out.println("The high conf is " + test.confidenceHigh());
    }
}
