
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;

    static int[][] dp;
    static int[] dx = {1, 0, -1, 0}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        dp = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                map[row][column] = sc.nextInt();
            }
            Arrays.fill(dp[row],-1);
        }

        solve(0, 0);
        System.out.println(dp[0][0]);
    }

    public static void solve(int row, int column) {

        dp[row][column] = 0;

        if (row == N - 1 && column == M - 1) {
            dp[N - 1][M - 1] = 1;
            return;
        }

        int height = map[row][column];
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M) {
                continue;
            }

            if (map[nextRow][nextColumn] < height) {

                if (dp[nextRow][nextColumn] >= 0 ) {
                    dp[row][column] += dp[nextRow][nextColumn];
                    continue;
                }

                solve(nextRow, nextColumn);
                dp[row][column] += dp[nextRow][nextColumn];
            }
        }
    }
}
