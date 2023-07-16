import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        solve(N);
    }

    public static void solve(int n) {
        long[][] dp = new long[n+1][10];
        Arrays.fill(dp[0], 1);

        for (int unit = 1; unit < n; unit++) {
            for (int number = 0; number < 10; number++) {

                if (number != 0) {
                    dp[unit][number] = (dp[unit][number] + dp[unit - 1][number - 1])  % 1000000000;
                }
                if (number != 9) {
                    dp[unit][number] = (dp[unit][number] +  dp[unit - 1][number + 1])  % 1000000000;
                }

            }
        }
        System.out.println((Arrays.stream(dp[n - 1]).sum() - dp[n-1][0]) % 1000000000);
    }
}
