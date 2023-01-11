import java.util.Scanner;

public class Main {

    static int N;
    static int dp[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        dp = new int[31];
        solve();
    }

    public static void solve() {

        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= N; i++) {
            
            if (i % 2 != 0) {
                dp[i] = 0;
                continue;
            }

            int sum = 0;

            for (int j = i; j > 2; j = j - 2) {
                sum += dp[i - j] * 2;
            }

            dp[i] = dp[i - 2] * dp[2] + sum;

        }
        System.out.println(dp[N]);
    }
}
