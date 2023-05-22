
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int N = sc.nextInt();
        int M = sc.nextInt();
        map = new int[N][M];
        dp = new int[N][M];

        for (int row = 0; row < N; row++) {
            String input = sc.next();

            for (int column = 0; column < M; column++) {
                map[row][column] = input.charAt(column);
            }
        }

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (map[row][column] == '1') {

                    if (row == 0 || column == 0) {
                        dp[row][column] = 1;
                        max = Math.max(dp[row][column], max);
                        continue;
                    }

                    dp[row][column] =
                            Math.min(dp[row][column - 1], Math.min(dp[row - 1][column - 1], dp[row - 1][column])) + 1;
                    max = Math.max(dp[row][column], max);
                }
            }
        }
        System.out.println(max * max);
    }


}
