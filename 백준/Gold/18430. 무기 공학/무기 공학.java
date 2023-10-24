import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int [][] result;
    static final int[] dy = {-1, 0, 1, 0, -1};
    static final int[] dx = {0, 1, 0, -1, 0};
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        result = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, 0);
        System.out.println(maxScore);
    }

    private static void solve(int startRow, int startColumn, int score) {

        for (int row = startRow; row < map.length; row++) {
            for (int column = startColumn; column < map[0].length; column++) {
                if (visited[row][column]) {
                    continue;
                }
                visited[row][column] = true;
                int nextScore = score + map[row][column] * 2;

                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = row + dy[dir];
                    int nextColumn = column + dx[dir];
                    int nextRow2 = row + dy[dir + 1];
                    int nextColumn2 = column + dx[dir + 1];

                    if (isOut(nextRow, nextColumn) || isOut(nextRow2, nextColumn2)
                            || visited[nextRow][nextColumn] || visited[nextRow2][nextColumn2]) {
                        continue;
                    }

                    visited[nextRow][nextColumn] = true;
                    visited[nextRow2][nextColumn2] = true;
                    solve(row, column +  1, nextScore + map[nextRow][nextColumn] + map[nextRow2][nextColumn2]);
                    visited[nextRow][nextColumn] = false;
                    visited[nextRow2][nextColumn2] = false;
                }
                visited[row][column] = false;
            }
            startColumn = 0;
        }

        maxScore = Math.max(maxScore, score);
    }

    private static boolean isOut(int nextRow, int nextColumn) {
        return nextRow < 0 || nextRow >= map.length || nextColumn < 0 || nextColumn >= map[0].length;
    }
}
