import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static List<Node> points;
    static boolean[][] visited;
    static int m;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        points = new ArrayList<>();
        visited = new boolean[map.length][map[0].length];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 1) {
                    visited[row][col] = true;
                } else if (map[row][col] == 2) {
                    points.add(new Node(row, col));
                }
            }
        }

        solve(0, 0, new int[m]);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {

            System.out.println(min);
        }
    }

    private static void solve(int idx, int count, int[] selected) {

        if (count == m) {
            // bfs
            boolean[][] copyVisited = new boolean[visited.length][visited.length];
            for (int row = 0; row < visited.length; row++) {
                copyVisited[row] = Arrays.copyOf(visited[row], visited.length);
            }
            int bfs = bfs(selected, copyVisited);

            if (bfs != -1 && min > bfs) {
                min = bfs;
            }
            return;
        }
        // m개를 선택해야하는데 , (map.length - 1) - idx + count < m
        if (idx >= points.size()) {
            return;
        }

        solve(idx + 1, count, Arrays.copyOf(selected, m));
        selected[count] = idx;
        solve(idx + 1, count + 1, Arrays.copyOf(selected, m));
    }

    private static int bfs(int[] selected, boolean[][] visited) {
        Queue<Node> que = new ArrayDeque<>();
        for (int select : selected) {
            Node point = points.get(select);
            que.offer(point);
            visited[point.row][point.col] = true;
        }
        int max = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (max < cur.time) {
                max = cur.time;
            }

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = cur.row + dy[idx];
                int nextCol = cur.col + dx[idx];

                if (nextRow >= map.length || nextCol >= map.length || nextRow < 0 || nextCol < 0
                        || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                que.offer(new Node(nextRow, nextCol, cur.time + 1));
            }
        }

        for (boolean[] booleans : visited) {
            if (Arrays.toString(booleans).contains("false")) {
                return -1;
            }
        }
        return max;
    }

    public static class Node {
        int row;
        int col;
        int time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
