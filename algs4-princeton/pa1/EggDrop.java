public class EggDrop {
    private static class Egg {
        private int floor;
        private int c;
        private int tosses;
        
        public Egg(int f){
            this.floor = f;
        }
        public boolean toss(int f) {
            tosses++;
            if (f >= this.floor){
                c++;
                return true;
            }
            return false;
        }
        public int getCount(){
            return c;
        }
        public int getTosses() {
            return tosses;
        }
    }
    
    public static void main(String[] args) {
        v0(100, new Egg(79));
        v1(100, new Egg(79));
        v2(100, new Egg(79));
        v3(100, new Egg(79));
    }
    /**
     * 1 egg, T tosses
     */
    public static int v0 (int n, Egg e) {
        for (int i = 0; i < n; i++) {
            if (e.toss(i)){
                System.out.println("find T after " + e.getTosses() + " tosses and broke "+ e.getCount() +" eggs, the result is " + i);
                return i;
            }
        }
        return -1;
    }
    
    /**
     * ?1lg(n) eggs and ?1lg(n) tosses
     */
    public static int v1(int n, Egg e) {
        int mid = n / 2;
        int begin = 0;
        int end = n;
        int res = 0;
        boolean lastToss = false;
        while (mid > begin && mid < end) {
            if (e.toss(mid)){
                end = mid;
                res = mid;
                mid = (begin + mid) / 2;
                lastToss = true;
            } else { 
                begin = mid;
                res = mid;
                mid = (mid + end) / 2;
                lastToss = false;
            }
        }
        if(!lastToss) res++;
        System.out.println(begin + "," + mid + "," + end + "," + lastToss);
        System.out.println("find T after " + e.getTosses() + " tosses and broke "+ e.getCount() +" eggs, the result is " + res);
        return res;
    }
    /**
     * ?lg(T) eggs and ?2lg(T) tosses.
     */
    public static int v2(int n, Egg e) {
        int begin = 0;
        int end = 1;
        while (!e.toss(end)) {
            begin = end;
            end *= 2;
        }
        int mid = (begin + end) / 2;
        int res = 0;
        boolean lastToss = false;
        while (mid > begin && mid < end) {
            if (e.toss(mid)){
                end = mid;
                res = mid;
                mid = (begin + mid) / 2;
                lastToss = true;
            } else { 
                begin = mid;
                res = mid;
                mid = (mid + end) / 2;
                lastToss = false;
            }
        }
        if(!lastToss) res++;
        System.out.println(begin + "," + mid + "," + end + "," + lastToss);
        System.out.println("find T after " + e.getTosses() + " tosses and broke "+ e.getCount() +" eggs, the result is " + res);
        return res;
    }
    /**
     * 2 eggs and ?2n? tosses
     */
    public static int v3(int n, Egg e) {
        int sqrtOfN = ((Double)Math.ceil(Math.sqrt(n))).intValue();
        int i=1;
        while(!e.toss(i*i) && i < sqrtOfN) {
            i++;
        }
        for(int j = (i-1) * (i-1); j < i * i; j++) {
            if(e.toss(j)) {
                System.out.println("find T after " + e.getTosses() + " tosses and broke "+ e.getCount() +" eggs, the result is " + j);
                return j;
            }
        }
        return 0;
    }
}