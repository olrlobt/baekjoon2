
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] dp;
    static int[] map;
    static int N;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];
        dp = new int[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        for (int num = 0; num < N; num++) {
            solve(num);
        }

        System.out.println(max);
    }

    public static void solve(int num) {

        if (num == 0) {
            dp[num] = map[num];
            max = dp[num];
            return;
        }

        if (dp[num - 1] + map[num] > map[num]){
            dp[num] = dp[num - 1] + map[num];
            if(max < dp[num]){
                max = dp[num];
            }
            return;
        }

        dp[num] = map[num];
        if(max < dp[num]){
            max = dp[num];
        }
    }
}
