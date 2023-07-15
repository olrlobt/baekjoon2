
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {
            int N = sc.nextInt();
            System.out.println(solve(N));
        }
    }

    public static long solve(int n){

        long [][] dp = new long[n+1][10];

        Arrays.fill(dp[0],1);
        long sum = 0;
        for (int unit = 1; unit <= n; unit++) {
            sum = 0;
            for(int num = 0 ; num < 10; num ++){
                sum += dp[unit - 1][num];
                dp[unit][num] = sum;
            }
        }
        return sum;
    }
}
