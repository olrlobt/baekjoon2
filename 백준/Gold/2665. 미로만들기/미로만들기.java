import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
    static int[] dy = {0, -1, 0, 1};
    static int[][] visited;
    static int INF;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new int[N][N];

        INF = N * N + 1; //기본 초기화값

        for (int row = 0; row < N; row++) {
            String[] input = sc.next().split("");

            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(input[column]);
            }
            Arrays.fill(visited[row], INF);
        }

        solve();

        System.out.println(visited[N-1][N-1]);
    }

    public static void solve() {

        Queue<Location> pq = new LinkedList<>();
        pq.offer(new Location(0, 0));
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            Location location = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = location.y + dy[i];
                int nextX = location.x + dx[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextY][nextX] <= visited[location.y][location.x]) {
                    continue;
                }
                if (map[nextY][nextX] == 0) { // 벽을 만났을때
                    visited[nextY][nextX] = Math.min(visited[location.y][location.x] + 1, visited[nextY][nextX]);
                } else {
                    visited[nextY][nextX] = Math.min(visited[location.y][location.x], visited[nextY][nextX]);
                }
                pq.offer(new Location(nextY, nextX));

            }
        }
    }

    public static class Location {
        int x;
        int y;

        public Location(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
}
