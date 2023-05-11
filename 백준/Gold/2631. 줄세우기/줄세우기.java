
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] map;
    static int[] dp;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];
        dp = new int[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        solve();

    }

    public static void solve() {

        int max = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {

                if (map[i] > map[j]){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }

            if(dp[i] > max){
                max = dp[i];
            }
        }

        System.out.println(N - max - 1);
    }
}
