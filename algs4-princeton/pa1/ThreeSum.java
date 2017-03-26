import java.util.Arrays;

public class ThreeSum {
    
    public static void main(String[] args){
        int[] array = new int[]{10, -10, -20, 30, -10, 0, 40};
        run(array);
    }
    
    public static void run(int[] array) {
        Arrays.sort(array);
        int n = array.length;
        int count = 0;
        for (int i = 0; i < n ; i++) {
            int j = i+1;
            int k = n-1;
            while(j < n-1 && j < k) {
                if (array[i] + array[j] + array[k] < 0 ) {
                    j++;
                } else if (array[i] + array[j] + array[k] == 0 ) {
                    count++;
                    System.out.println(array[i] + "," + array[j] + "," + array[k]);
                    j++;
                } else {
                    k--;
                }
            }
        }
    }
}