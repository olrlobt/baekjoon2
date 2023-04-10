
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] map;
    static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        map = new int[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }
        M = sc.nextInt();
        Arrays.sort(map);
        solve();
    }

    public static void solve() {

        int low = 0 ;
        int high = map[map.length-1];

        while(low <= high){
            int mid = (low+high)/2;

            if(cal(mid) > M){
                high = mid-1;
            }else{
                low = mid +1 ;
            }
        }

        System.out.println(low-1 );
    }

    public static int cal(int mid){
        int count = 0;

        for(int i : map){
            if(i < mid ){
                count += i;
            }else{
                count += mid;
            }
        }
        return count;
    }
}
