public class CanonicalUnionFind {
    private int n;
    private int[] elements;
    
    public CanonicalUnionFind(int n) {
        this.n = n;
        elements = new int[n];
        for(int i = 0; i < n; i++) {
            elements[i] = i;
        }
    }
    public static void main(String[] args) {
        CanonicalUnionFind cuf = new CanonicalUnionFind(10);
        cuf.union(1, 2);
        cuf.union(2, 6);
        cuf.union(1, 9);
        int res1 = cuf.find(1);
        int res2 = cuf.find(2);
        int res3 = cuf.find(6);
        int res4 = cuf.find(9);
        System.out.println(res1 + "," + res2 + "," + res3 + "," + res4);
    }
    
    public void union(int i, int j) {
        int ri = getRoot(i);
        int rj = getRoot(j);
        if(rj != ri) {
            if (ri < rj) {
                elements[ri] = rj;
            } else {
                elements[rj] = ri;
            }
        }
    }
    private int getRoot(int i) {
        while(i != elements[i])
            i = elements[i];
        return i;
    }
    public boolean connected(int i, int j) {
        return getRoot(i) == getRoot(j);
    }
    
    public int find(int i) {
        return getRoot(i);
    }
}