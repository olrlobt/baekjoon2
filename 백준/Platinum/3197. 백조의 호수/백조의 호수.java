
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int R, C;
    static List<Node> swans = new ArrayList<>(2);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        Queue<Node> pq = new ArrayDeque<>();
        for (int row = 0; row < R; row++) {
            String input = br.readLine();
            for (int col = 0; col < C; col++) {
                map[row][col] = input.charAt(col);
                if (map[row][col] == '.') {
                    pq.offer(new Node(row, col));
                } else if (map[row][col] == 'L') {
                    swans.add(new Node(row, col));
                    pq.offer(new Node(row, col));
                }
            }

        }
        solve(pq);
    }

    private static void solve(Queue<Node> pq) {

        int[][] timeTable = new int[R][C];

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dy[i];
                int nextCol = cur.col + dx[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C
                        || map[nextRow][nextCol] == '.' || timeTable[nextRow][nextCol] != 0) {
                    continue;
                }
                timeTable[nextRow][nextCol] = cur.time + 1;
                pq.offer(new Node(nextRow, nextCol, cur.time + 1));
            }

        }

        solve2(timeTable);
    }

    private static void solve2(int[][] timeTable) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(swans.get(0));
        boolean[][] visited = new boolean[R][C];
        int time = 0;
        visited[swans.get(0).row][swans.get(0).col] = true;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();
            if (timeTable[cur.row][cur.col] > time) {
                time = timeTable[cur.row][cur.col];
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dy[i];
                int nextCol = cur.col + dx[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C
                || visited[nextRow][nextCol]) {
                    continue;
                }
                if (map[nextRow][nextCol] == 'L') {
                    System.out.println(time);
                    return;
                }

                visited[nextRow][nextCol] = true;
                pq.offer(new Node(nextRow, nextCol, timeTable[nextRow][nextCol]));
            }
        }
    }



    private static class Node implements Comparable<Node> {
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

        @Override
        public int compareTo(Node o) {
            return Integer.compare(time, o.time);
        }
    }
}
