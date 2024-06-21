import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        int K = Integer.parseInt(st.nextToken()); // 이동횟수
        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        solve(map, K);
    }

    private static void solve(int[][] map, int k) {
        int point = 0;
        Dice dice = new Dice(0, 0, 0, new Status(1, new int[]{4, 3}, new int[]{2, 5}));
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        for (int move = 0; move < k; move++) {

            // 주사위 이동
            dice.row += dy[dice.dir];
            dice.col += dx[dice.dir];
            while (dice.row >= N || dice.row < 0 || dice.col >= M || dice.col < 0) {
                dice.row -= dy[dice.dir];
                dice.col -= dx[dice.dir];
                dice.reverse();
                dice.row += dy[dice.dir];
                dice.col += dx[dice.dir];
            }

            dice.roll();

            if (map[dice.row][dice.col] < 7 - dice.status.cur) {
                dice.dir++;
                dice.dir %= 4;
            } else if (map[dice.row][dice.col] > 7 - dice.status.cur) {
                dice.dir--;
                if (dice.dir < 0) {
                    dice.dir += 4;
                }
            }

            // 점수 탐색
            queue.offer(new Node(dice.row, dice.col));
            int num = map[dice.row][dice.col];
            visited[dice.row][dice.col] = true;

            point += num;
            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (int idx = 0; idx < 4; idx++) {
                    int nextRow = cur.row + dy[idx];
                    int nextCol = cur.col + dx[idx];

                    if (nextRow >= N || nextRow < 0 || nextCol >= M || nextCol < 0
                            || visited[nextRow][nextCol]) {
                        continue;
                    } else if (map[nextRow][nextCol] == num) {
                        point += num;
                        visited[nextRow][nextCol] = true;
                        queue.offer(new Node(nextRow, nextCol));
                    }
                }
            }
            for (int row = 0; row < N; row++) {
                Arrays.fill(visited[row], false);
            }
        }

        System.out.println(point);
    }

    public static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class Dice extends Node {
        int dir; // 동 서 남 북
        Status status;

        public Dice(int row, int col, int dir) {
            super(row, col);
            this.dir = dir;
        }

        public Dice(int row, int col, int dir, Status status) {
            super(row, col);
            this.dir = dir;
            this.status = status;
        }

        public void reverse() {
            this.dir += 2;
            if (dir > 3) {
                dir -= 4;
            }
        }

        public void roll() {
            status.roll(dir);
        }
    }

    public static class Status {
        int cur;
        int[] row;
        int[] col;

        public Status(int cur, int[] row, int[] col) {
            this.cur = cur;
            this.row = row;
            this.col = col;
        }

        public void roll(int dir) {
            // 동 남 서 북

            int flag = 0;
            if (dir < 2) { // ++
                flag = 1;
            }

            if (dir % 2 == 0) { // 가로
                row[flag] = cur;
                cur = row[1 - flag];
                row[1 - flag] = 7 - row[flag];
            } else { // 세로
                col[flag] = cur;
                cur = col[1 - flag];
                col[1 - flag] = 7 - col[flag];
            }
        }
    }
}
