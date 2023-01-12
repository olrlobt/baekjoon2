
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static long[][] dp;
    static int K;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        dp = new long[K][N + 1];

        solve();
    }

    public static void solve() {
        for (int i = 0; i < K ; i ++) {
            Arrays.fill(dp[i],1);
        }

        for (int i = 1; i < K ; i ++) {
            for(int j = 1; j <= N; j ++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
                
            }
        }

        System.out.println(dp[K-1][N] % 1000000000);
    }
}
