
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[M][N];
        visited = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            String[] input = sc.next().split("");

            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(input[column]);
            }
        }

        solve();
    }

    public static void solve() {

        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.offer(new Location(0, 0, 0));

        while (!pq.isEmpty()) {

            Location location = pq.poll();

            if (location.y == M - 1 && location.x == N - 1) {
                System.out.println(location.wall);
                return;
            }

            if(visited[location.y][location.x]){
                continue;
            }
            visited[location.y][location.x] = true;

            for (int i = 0; i < 4; i++) {
                int nextY = location.y + dy[i];
                int nextX = location.x + dx[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                if (map[nextY][nextX] == 1) {
                    pq.offer(new Location(nextY, nextX, location.wall + 1));
                } else {
                    pq.offer(new Location(nextY, nextX, location.wall));
                }

            }
        }
    }

    public static class Location implements Comparable<Location> {
        int x;
        int y;
        int wall;

        public Location(int y, int x, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }

        @Override
        public int compareTo(Location o) {
            return wall - o.wall;
        }
    }

}
