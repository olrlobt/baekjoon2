
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {
                    map[row][column] = sc.nextInt();
                }
            }

            int[] startPoint = new int[]{sc.nextInt(), sc.nextInt()};
            int[] endPoint = new int[]{sc.nextInt(), sc.nextInt()};

            System.out.println("#" + testCase + " " + solve(map, startPoint, endPoint));
        }
    }

    private static int solve(int[][] map, int[] startPoint, int[] endPoint) {
        PriorityQueue<Samsung> queue = new PriorityQueue<>();
        int[][] visited = new int[map.length][map[0].length];
        queue.offer(new Samsung(startPoint[0], startPoint[1], 0));

        while (!queue.isEmpty()) {

            Samsung curSamsung = queue.poll();

            if (curSamsung.row == endPoint[0] && curSamsung.column == endPoint[1]) {
                return curSamsung.time;
            }
            if (visited[curSamsung.row][curSamsung.column] != 0
                    && visited[curSamsung.row][curSamsung.column] < curSamsung.time) {
                continue;
            }
            visited[curSamsung.row][curSamsung.column] = curSamsung.time;

            for (int i = 0; i < 4; i++) {
                int nextRow = curSamsung.row + dy[i];
                int nextColumn = curSamsung.column + dx[i];

                if (nextRow < 0 || nextRow >= map.length || nextColumn < 0 || nextColumn >= map.length
                        || map[nextRow][nextColumn] == 1) {
                    continue;
                }
                if (map[nextRow][nextColumn] == 2) {
                    int nextTime = curSamsung.time;
                    if (curSamsung.time == 0 || curSamsung.time % 3 == 2) {
                        nextTime -= 1;
                    }
                    nextTime = nextTime + (3 - nextTime % 3);
                    queue.offer(new Samsung(nextRow, nextColumn, nextTime));
                    continue;
                }
                queue.offer(new Samsung(nextRow, nextColumn, curSamsung.time + 1));
            }
        }
        return -1;
    }

    private static class Samsung implements Comparable<Samsung> {

        int row;
        int column;
        int time;

        public Samsung(int row, int column, int time) {
            this.row = row;
            this.column = column;
            this.time = time;
        }

        @Override
        public int compareTo(Samsung o) {
            return time - o.time;
        }
    }
}
