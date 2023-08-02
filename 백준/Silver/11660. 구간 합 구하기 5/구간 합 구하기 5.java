import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int [][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int column = 0; column < N; column++) {
                map[i][column] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][N+1];

        for (int row = 1; row <= N; row++) {
            for (int column = 1; column <= N; column++) {
                dp[row][column] += dp[row][column-1] + map[row-1][column-1];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int x1, int y1, int x2, int y2) {
        int sum = 0;

        for (int row = x1; row <= x2; row++) {
            sum += dp[row][y2] - dp[row][y1 - 1];
        }

        return sum;
    }

}
