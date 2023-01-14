
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] maze;
    static int N;
    static int M;

    static int[] dx = {0, 1, 0, -1}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {-1, 0, 1, 0};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        maze = new int[N + 2][M + 2];

        for (int row = 1; row <= N; row++) {
            String[] rowInput = sc.next().split("");

            for (int column = 1; column <= M; column++) {
                maze[row][column] = Integer.parseInt(rowInput[column - 1]);
            }
        }
        bfs();
    }

    private static void bfs() {
        int[][] visited = new int[N +2][M +2];
        Queue<int[]> queue = new LinkedList<>();

        visited[1][1] = 1;
        queue.offer(new int[]{1, 1});

        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            int row = node[0];
            int column = node[1];

            for (int i = 0; i < 4; i++) {

                if (maze[row + dy[i]][column + dx[i]] == 1 && visited[row + dy[i]][column + dx[i]] == 0) {
                    queue.offer(new int[]{row + dy[i], column + dx[i]});
                    visited[row + dy[i]][column + dx[i]] = visited[row][column] + 1;
                }
            }
        }

        System.out.println(visited[N][M]);
    }
}
