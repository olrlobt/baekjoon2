import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp = new int[4][10_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        Arrays.fill(dp[1], 1);
        dp[2][2] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int solve = solve(N, 2) + solve(N, 3) + 1;
            sb.append(solve).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int N, int idx) {

        if (dp[idx][N] != 0 || N < 4) {
            return dp[idx][N];
        }

        if (idx == 3) {
            dp[3][N] = solve(N - 3, 3) + solve(N - 3, 2) + 1;
            return dp[3][N];
        }
        dp[2][N] = solve(N - 2, 2) + 1;
        return dp[2][N];
    }
}
