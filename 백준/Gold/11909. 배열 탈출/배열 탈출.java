
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N][N];
        dp = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
            Arrays.fill(dp[row], Integer.MAX_VALUE);
        }
        solve();

        System.out.println(dp[N-1][N-1]);

    }

    public static void solve() {

        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {



                if(j != 0){
                    if (map[i][j] - map[i][j - 1] < 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j],dp[i][j-1] + map[i][j] - map[i][j - 1] + 1);
                    }
                }

                if(i != 0){
                    if (map[i][j] - map[i-1][j] < 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j],dp[i-1][j] + map[i][j] - map[i - 1][j] + 1);
                    }
                }

                if (i == N - 1 && j == N - 1) {
                    return;
                }
            }
        }
    }
}
