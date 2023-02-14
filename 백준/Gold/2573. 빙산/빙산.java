
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N, M;
    static int[][] visited;
    static final int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
    static final int[] dy = {0, -1, 0, 1};


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
        int year = 0;

        while (!count()){
            if(!melt()){
                year = 0;
                break;
            }
            year++;
        }

        System.out.println(year);
    }

    private static boolean melt() {
        int[][] newMap = new int[N][M];
        boolean check = false;

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (map[row][column] != 0) {
                    int zero = 0;
                    check = true;
                    for(int i = 0 ; i < 4; i ++){
                        if(map[row + dy[i]][column + dx[i]] == 0){
                            zero ++;
                        }
                    }

                    newMap[row][column] = map[row][column] - zero;
                    if(newMap[row][column] < 0){
                        newMap[row][column] = 0;
                    }
                }
            }
        }

        map = newMap;

        if(!check){
            return false;
        }
        return true;
    }

    private static boolean count() {
        int count = 0;
        visited = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (map[row][column] != 0 && visited[row][column] == 0) {
                    if (count >= 1) {
                        return true;
                    }
                    Island(row, column, ++count);

                }
            }
        }



        return false;
    }


    private static void Island(int row, int column, int count) {
        visited[row][column] = count;
        for (int i = 0; i < 4; i++) {
            if (map[row + dy[i]][column + dx[i]] != 0 && visited[row + dy[i]][column + dx[i]] == 0) {
                Island(row + dy[i], column + dx[i], count);
            }
        }

    }
}
