
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int[][] visited;

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited = new int[N][M];

        for (int row = 0; row < N; row++) {
            String input = sc.next();
            for (int column = 0; column < M; column++) {

                map[row][column] = input.charAt(column) - '0';

            }
        }

        boolean result = false;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {

                if (visited[row][column] == 0) {
                    result = ( result || solve(row, column, 1));
                }
            }
        }

        if(result){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

    }

    public static boolean solve(int row, int column, int count) {

        if(visited[row][column] != 0){
            if(visited[row][column] == count - 2){
                return false;
            }

            return true;
        }
        visited[row][column] = count;
        int cur = map[row][column];

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= M) {
                continue;
            }
            if (cur == map[nextRow][nextColumn]) {
                if(solve(nextRow, nextColumn, count + 1)){
                    return true;
                }
            }
        }

        return false;
    }
}
