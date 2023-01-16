import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {-1, 0, 1, 0};

    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        solve();


    }
    
    public static void solve() {

        wall(0);
        System.out.println(max);
    }

    private static void wall(int count) {

        if (count == 3) {
            max = Math.max(max, count());
            return;
        }

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (map[row][column] == 0) {

                    map[row][column] = 1;
                    wall(count + 1);
                    map[row][column] = 0;
                }
            }
        }
    }


    public static int count() {
        int count = 0;
        int [][] newMap = new int[N][M];

        for(int row = 0; row < N ; row ++){
            System.arraycopy(map[row],0, newMap[row],0,M);
        }

        virus(newMap);

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (newMap[row][column] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void virus(int [][] newMap) {

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {

                if (newMap[row][column] == 2) {
                    spread(row, column , newMap);
                }
            }
        }
    }

    private static void spread(int row, int column , int [][] newMap) {

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= M) {
                continue;
            }

            if (newMap[row + dy[i]][column + dx[i]] == 0) {
                newMap[row + dy[i]][column + dx[i]] = 2;
                spread(row + dy[i], column + dx[i] , newMap);
            }
        }
    }
}
