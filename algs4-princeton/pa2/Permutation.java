import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (0 == k) return;
        // In in = new In(args[2]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int c = 0;
        double p = 1.0d / (k+1); 
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (c >= k) {
                if (StdRandom.bernoulli(p)) {
                    rq.dequeue();
                    rq.enqueue(str);
                }
            } else {
                rq.enqueue(str);
                c++;
            }
        }
        Iterator<String> it = rq.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
    }
}