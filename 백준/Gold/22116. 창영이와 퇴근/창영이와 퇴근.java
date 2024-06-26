import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[][] map = new long[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                map[row][col] = Long.parseLong(st.nextToken());
            }
        }

        System.out.println(solve(map));
    }

    private static long solve(long[][] map) {

        boolean[][] visited = new boolean[map.length + 1][map.length + 1];
//        visited[1][1] = true;

        Arrays.fill(visited[0], true);
        Arrays.fill(visited[visited.length - 1], true);
        for (int row = 0; row < visited.length - 1; row++) {
            visited[row][0] = true;
            visited[row][visited.length - 1] = true;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.row][cur.col]) {
                continue;
            }
            visited[cur.row][cur.col] = true;

            if (cur.row == map.length - 1 && cur.col == map.length - 1) {
                return cur.maxDiff;
            }

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = cur.row + dy[idx];
                int nextCol = cur.col + dx[idx];
                if (visited[nextRow][nextCol]) {
                    continue;
                }

                long max = Math.max(cur.maxDiff, Math.abs(map[nextRow][nextCol] - map[cur.row][cur.col]));
                pq.offer(new Node(nextRow, nextCol, max));
            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node> {
        int row;
        int col;
        long maxDiff;

        public Node(int row, int col, long maxDiff) {
            this.row = row;
            this.col = col;
            this.maxDiff = maxDiff;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(maxDiff, o.maxDiff);
        }
    }
}
