public class BitonicSearch {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 7, 9, 8, 6};
        System.out.println("index of 3 is " + find(array, 3));
        System.out.println("index of 8 is " + find(array, 8));
    }
    
    public static int find(int[] a, int num) {
        int n = a.length;
        int maxIdx = n/2;
        int begin = 0;
        int end = n;
        while(maxIdx > 0 && maxIdx < n) {
            if(a[maxIdx] == num) return maxIdx;
            if(a[maxIdx] > a[maxIdx - 1]){
                if(a[maxIdx] > a[maxIdx + 1])
                    break;
                else
                    maxIdx = (maxIdx + end) / 2;
            } else {
                maxIdx = (begin + maxIdx) / 2;
            }
        }
        int idx = binarySearch(a, num, 0, maxIdx, true);
        if(idx == -1)
            idx = binarySearch(a, num, maxIdx, n, false);
        return idx;
    }
    
    private static int binarySearch(int[] a, int num, int begin, int end, boolean asc){
        int mid = (begin + end) / 2;
        while(mid > begin && mid < end) {
            if(a[mid] == num) return mid;
            if(a[mid] < num){
                if(asc) mid = (mid + end) / 2;
                else mid = (begin + mid) / 2;
            }else{
                if(asc) mid = (begin + mid) / 2;
                else mid = (mid + end) / 2;
            }
        }
        return -1;
    }
    
}