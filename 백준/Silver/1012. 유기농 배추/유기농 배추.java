
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {

            int M = sc.nextInt();
            int N = sc.nextInt(); //ROW
            int K = sc.nextInt();
            map = new int[M][N];
            visited = new boolean[M][N];

            while (K-- > 0) {
                map[sc.nextInt()][sc.nextInt()] = 1;
            }

            solve(M, N);
        }
    }

    public static void solve(int row, int column) {
        int count = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (visited[i][j] || map[i][j] == 0) {
                    continue;
                }
                count++;
                bfs(i, j);


            }
        }
        System.out.println(count - 1);
    }

    private static void bfs(int row, int column) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, column});

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + dy[i];
                int nextColumn = cur[1] + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= visited.length || nextColumn >= visited[0].length) {
                    continue;
                }
                if(map[nextRow][nextColumn] == 0){
                    continue;
                }

                queue.offer(new int[]{nextRow, nextColumn});

            }
        }
    }
}
