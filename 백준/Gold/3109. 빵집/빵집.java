import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Character[][] map;
    static boolean[][] visited;
    static int result = 0;
    static int R;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        visited = new boolean[R][C];
        map = new Character[R][C];
        for (int row = 0; row < R; row++) {
            String input = sc.next();
            for (int column = 0; column < C; column++) {
                map[row][column] = input.charAt(column);
                if (map[row][column] == 'x') {
                    visited[row][column] = true;
                }
            }
        }

        for (int row = 0; row < R; row++) {
            solve(row, 0);
        }
        System.out.println(result);
    }

    public static boolean solve(int row, int column) {

        if (column == C - 1) {
            result++;
            return true;
        }

        if (visited[row][column]) {
            return false;
        }
        visited[row][column] = true;
        if (row != 0 && !visited[row - 1][column + 1]) {

            if(solve(row - 1, column + 1)){
                return true;
            }

        }

        if (!visited[row][column + 1]) {
            if(solve(row, column + 1)){
                return true;
            }
        }

        if (row != R-1 && !visited[row + 1][column + 1]) {
            if(solve(row + 1, column + 1)){
                return true;
            }
        }
        return false;
    }
}
