
import java.util.Scanner;

public class Main {

    static int N;
    static String[][] RGB;
    static int[][] visited;
    static int[] dx = {0, 1, 0, -1}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        RGB = new String[N + 2][N + 2];

        for (int row = 1; row <= N; row++) {
            String[] rowInput = sc.next().split("");

            for (int column = 1; column <= N; column++) {
                RGB[row][column] = rowInput[column - 1];
            }
        }

        solve();

        for (int row = 1; row <= N; row++) {
            for (int column = 1; column <= N; column++) {
                if (RGB[row][column].equals("G")) {
                    RGB[row][column] = "R";
                }
            }
        }
        System.out.print(" ");
        solve();
    }


    public static void solve() {
        int type = 1;
        visited = new int[N + 2][N + 2];

        for (int row = 1; row <= N; row++) {
            for (int column = 1; column <= N; column++) {

                if (visited[row][column] == 0) {
                    visited[row][column] = type;
                    dfs(row, column);
                    type++;
                }
            }
        }

        System.out.print(type-1);
    }

    public static void dfs(int row, int column) {

        String color = RGB[row][column];

        for (int i = 0; i < 4; i++) {
            if (RGB[row + dy[i]][column + dx[i]] != null && RGB[row + dy[i]][column + dx[i]].equals(color)
                    && visited[row + dy[i]][column + dx[i]] == 0) {
                visited[row + dy[i]][column + dx[i]] = visited[row][column];
                dfs(row + dy[i], column + dx[i]);
            }
        }


    }
}
