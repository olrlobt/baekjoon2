
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        dp = new int[N ][N ];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
        }
        solve(1, 0, 0);
        System.out.println(dp[N - 1][N - 1]);
    }

    public static void solve(int x, int y, int direction) {
        // direction    0-가로 1-대각 2-세로

        if (x == N || y == N) {
            return;
        }

        if (x == N - 1 && y == N - 1) {
            return;
        }

        if (direction != 2 && x + 1 != N && map[y][x + 1] != 1) {

            dp[y][x + 1]++;
            solve(x + 1, y, 0);
        }

        if (direction != 0 && y + 1 != N && map[y + 1][x] != 1) {

            dp[y + 1][x]++;
            solve(x, y + 1, 2);
        }

        if (x + 1 != N && y + 1 != N && map[y + 1][x + 1] != 1 && map[y + 1][x ] != 1 && map[y ][x + 1] != 1) {

            dp[y + 1][x + 1]++;
            solve(x + 1, y + 1, 1);
        }


    }
}
