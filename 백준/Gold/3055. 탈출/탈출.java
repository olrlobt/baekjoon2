
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static Character[][] map;
    static int[] end;
    static int[] start;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new Character[N][M];
        for (int i = 0; i < N; i++) {
            String input = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j].equals('D')) {
                    end = new int[]{i, j};
                } else if (map[i][j].equals('S')) {
                    start = new int[]{i, j};
                }

            }
        }

        solve();
    }

    public static void solve() {

        boolean[][] visited = new boolean[N][M];
        boolean check = true;
        int count = 0 ;
        String result = "KAKTUS";
        while (check){
            count ++;

            Queue<int[]> queue = new LinkedList<>();
            check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j].equals('S')) {
                        queue.offer(new int[]{i, j});
                        check = true;
                    }
                }
            }

            while (!queue.isEmpty()) {

                int[] cur = queue.poll();

                map[cur[0]][cur[1]] = '.';
                if(visited[cur[0]][cur[1]]){
                    continue;
                }
                visited[cur[0]][cur[1]] = true;

                for (int i = 0; i < 4; i++) {
                    int nextX = dx[i] + cur[1];
                    int nextY = dy[i] + cur[0];
                    if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N || visited[nextY][nextX]) {
                        continue;
                    }
                    if (map[nextY][nextX].equals('.')) {
                        map[nextY][nextX] = 'S';
                    }
                    if (map[nextY][nextX].equals('D')) {
                        check = false;
                        result = String.valueOf(count);
                    }
                }
            }
            water();
        }

        System.out.println(result);
    }

    private static void water() {

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals('*')) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {

            int[] curWater = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nextX = dx[i] + curWater[1];
                int nextY = dy[i] + curWater[0];
                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue;
                }
                if (map[nextY][nextX].equals('.') || map[nextY][nextX].equals('S')) {
                    map[nextY][nextX] = '*';
                }
            }
        }
    }
}
