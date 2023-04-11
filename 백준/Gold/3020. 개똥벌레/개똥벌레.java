
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int H;
    static int[] top;
    static int[] bottom;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        H = sc.nextInt();
        top = new int[N/2];
        bottom = new int[N/2];
        for (int num = 0; num < N/2; num++) {

            bottom[num] = sc.nextInt();
            top[num] = H - sc.nextInt();
        }
        Arrays.sort(top);
        Arrays.sort(bottom);
        solve();
    }

    public static void solve(){

        int min = Integer.MAX_VALUE;
        int c = 1;
        for(int i = 1 ; i <= H; i ++){

            int count = count(top,i) + (bottom.length -  count(bottom, i));

            if(count == min){
                c ++;
            }else if(min > count){
                c = 1;
                min = count;
            }
        }
        System.out.println(min + " " + c);

    }

    public static int count(int [] map, int i){

        int low = 0;
        int high = map.length - 1;

        while (low <= high){

            int mid = (low + high) /2;
            if(map[mid] < i){
                low = mid + 1;
            }else{
                high = mid-1;
            }
        }
        return low;

    }

}
