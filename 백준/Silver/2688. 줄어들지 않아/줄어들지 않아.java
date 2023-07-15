
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solve(N)).append("\n");
        }
        System.out.print(sb);
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
