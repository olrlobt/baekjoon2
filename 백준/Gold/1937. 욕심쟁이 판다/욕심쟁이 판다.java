
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static int[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        int max = 0;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                max = Math.max(max , solve(row, column));
            }
        }

        System.out.println(max + 1 );
    }

    public static int solve(int row, int column) {
        int result = 0;
        if(visited[row][column] != 0){
            return visited[row][column];
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextColumn < 0 || nextRow < 0 || nextColumn >= N || nextRow >= N) {
                continue;
            }
            if(map[nextRow][nextColumn] <= map[row][column]){
                continue;
            }
            result = Math.max(result, solve(nextRow, nextColumn) + 1);
        }

        visited[row][column] = result;
        return result;
    }
}
