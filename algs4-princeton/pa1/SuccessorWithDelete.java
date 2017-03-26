public class SuccessorWithDelete {
    
    private int n;
    private int[] elements;
    
    public SuccessorWithDelete(int n) {
        this.n = n;
        elements = new int[n];
        for(int i = 0; i < n; i++) {
            elements[i] = i;
        }
    }
    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        System.out.println("successor of 3 " + swd.successor(3));
        swd.delete(3);
        System.out.println("successor of 3 " + swd.successor(3));
        swd.delete(4);
        swd.delete(6);
        swd.delete(1);
        System.out.println("successor of 3 " + swd.successor(3));
        swd.delete(5);
        System.out.println("successor of 3 " + swd.successor(3));
    }
    
    public int successor(int i) {
        return find(i);
    }
    
    public void delete(int i) {
        union(i, i+1);
    }
    
    private void union(int i, int j) {
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
    private boolean connected(int i, int j) {
        return getRoot(i) == getRoot(j);
    }
    
    private int find(int i) {
        return getRoot(i);
    }
}