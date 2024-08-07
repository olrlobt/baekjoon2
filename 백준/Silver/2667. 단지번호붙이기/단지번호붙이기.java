import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        int count = 0;
        PriorityQueue<Integer> result = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 1 && !visited[i][j]) {
                    result.offer(solve(i, j));
                    count++;
                }

            }
        }
        sb.append(count).append('\n');
        while (!result.isEmpty()){
            sb.append(result.poll()).append('\n');
        }
        System.out.println(sb);
    }

    public static int solve(int row, int column) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, column});
        int count = 1;
        visited[row][column] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            int curRow = cur[0];
            int curColumn = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dy[i];
                int nextColumn = curColumn + dx[i];

                if (nextColumn < 0 || nextRow < 0 || nextRow >= N || nextColumn >= N || map[nextRow][nextColumn] == 0
                || visited[nextRow][nextColumn]) {
                    continue;
                }
                visited[nextRow][nextColumn] = true;
                count++;
                queue.offer(new int[]{nextRow, nextColumn});
            }
        }
        return count;
    }
}
