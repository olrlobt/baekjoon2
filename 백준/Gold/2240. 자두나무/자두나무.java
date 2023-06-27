
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int W = sc.nextInt();
        int[] map = new int[T];
        for (int tree = 0; tree < T; tree++) {
            map[tree] = sc.nextInt();
        }

        solve(map, W);
    }

    public static void solve(int[] map, int W) {
        int[][] dp = new int[map.length + 1][W + 1];

        for (int i = 1; i < map.length + 1; i++) {

            for (int j = 0; j < W + 1; j++) { // w 가 홀수면 2번 나무 / 짝수면 1번 나무
                int count = 1;
                if (map[i - 1] % 2 == j % 2) {
                    count = 0;
                }

                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + count;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + count;
                }

            }
        }

        System.out.println(Math.max(dp[map.length][W] , dp[map.length][W-1]));
    }
}
