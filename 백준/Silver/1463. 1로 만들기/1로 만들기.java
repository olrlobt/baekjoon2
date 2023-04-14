
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;

        while (N > 0) {

            if (N % 3 == 0) {
                dp[N / 3] = Math.min(dp[N / 3], dp[N]+1);
            }
            if (N % 2 == 0) {
                dp[N / 2] = Math.min(dp[N / 2], dp[N]+1);
            }
            dp[N-1] = Math.min(dp[N-1], dp[N]+1);

            N--;
        }

        System.out.println(dp[1]);
    }
}