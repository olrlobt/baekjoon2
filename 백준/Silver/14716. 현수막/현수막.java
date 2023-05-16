
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int M;
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[M][N];
        visited = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        for (int row = 0; row < M; row++) {
            for (int column = 0; column < N; column++) {

                if (map[row][column] == 0 || visited[row][column]) {
                    continue;
                }
                solve(row, column);
                count++;
            }
        }

        System.out.println(count);
    }

    public static void solve(int row, int column) {

        visited[row][column] = true;

        for (int i = 0; i < 8; i++) {

            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= M || nextColumn >= N) {
                continue;
            }

            if (visited[nextRow][nextColumn] || map[nextRow][nextColumn] == 0) {
                continue;
            }

            solve(nextRow, nextColumn);
        }
    }
}
