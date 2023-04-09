
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        N = sc.nextInt();

        map = new int[K];
        for (int lan = 0; lan < K; lan++) {
            map[lan] = sc.nextInt();
        }
        Arrays.sort(map);
        System.out.println(solve());

    }

    public static long solve() {

        long high = map[map.length-1]; // high
        long low = 0; // low
        while (low <= high) {
            int count = 0;
            long mid = (high + low) / 2;
            if(mid == 0){
                mid++;
            }

            for (int i : map) {
                count += i / mid;
            }

            if(count < N){
                high =  mid-1;
            }
            else if(count >= N){
                low = mid + 1 ;
            }
        }

        return high;
    }
}
