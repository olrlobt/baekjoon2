
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static int M;
    static boolean[][] visited;
    static int num = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        int max = 0;
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {

                if (visited[row][column] || map[row][column] == 0) {
                    continue;
                }
                num = 1;
                count ++;
                solve(row, column);

                if(num > max){
                    max = num;
                }
            }
        }

        System.out.println(count);
        System.out.println(max);

    }

    public static void solve(int row, int column) {

        visited[row][column] = true;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];


            if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= M) {
                continue;
            }
            if (visited[nextRow][nextColumn]) {
                continue;
            }

            if(map[nextRow][nextColumn] == 1){
                solve(nextRow, nextColumn);
                num ++;
            }

        }
    }

}
