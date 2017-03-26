import edu.princeton.cs.algs4.In;

public class SocialNetworkConnectivity {
    private int n;
    private int[] users;
    
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // javn-by-n percolation system

        // repeatedly read in sites to open and draw resulting system
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(n);
        
        while (!in.isEmpty()) {
            long t = in.readLong();
            int i = in.readInt() - 1;
            int j = in.readInt() - 1;
            snc.connect(i, j);
            if(snc.isFullyConnected()) {
                System.out.println("all users are connected @" + t);
                break;
            }                
        }
    }
    
    public SocialNetworkConnectivity(int n) {
        this.n = n;
        users = new int[n];
        for(int i = 0; i < n; i++) {
            users[i] = i;
        }
    }
    private int getRoot(int i) {
        while(i != users[i])
            i = users[i];
        return i;
    }
    private void connect(int i, int j) {
        int ri = getRoot(i);
        int rj = getRoot(j);
        if(rj != ri)
            users[ri] = rj;
    }
    private boolean isConnected(int i, int j) {
        return getRoot(i) == getRoot(j);
    }
    private boolean isFullyConnected() {
        int c = 0;
        for(int i = 0; i < n; i++) {
            if(i == users[i])
                c++;
        } 
        return c==1;
    }
}