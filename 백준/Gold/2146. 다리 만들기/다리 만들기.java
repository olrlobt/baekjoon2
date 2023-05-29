
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static boolean[][] visited;

    static int count = 1;
    static int N;
    static int result = Integer.MAX_VALUE;

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {

                map[row][column] = sc.nextInt();
            }
        }

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {

                if (map[row][column] == 1 && !visited[row][column]) {
                    sort(row, column);
                    count++;
                }
            }
        }

        visited = new boolean[N][N];
        int count = 1;
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {

                if (map[row][column] == count) {
                    visited = new boolean[N][N];
                    solve(row, column);
                    count++;
                }

            }
        }

        System.out.println(result-1);
    }

    private static void solve(int row, int column) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(row,column,0));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (visited[curNode.row][curNode.column] || curNode.count >= result) {
                continue;
            }
            visited[curNode.row][curNode.column] = true;

            if (map[curNode.row][curNode.column] != map[row][column] && map[curNode.row][curNode.column] != 0) {
                result = Math.min(result, curNode.count);
            }

            for (int i = 0; i < 4; i++) {

                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= N) {
                    continue;
                }

                if (map[nextRow][nextColumn] == map[row][column]) {
                    queue.offer(new Node(nextRow, nextColumn, curNode.count));
                } else {
                    queue.offer(new Node(nextRow, nextColumn, curNode.count + 1));
                }
            }
        }
    }

    public static void sort(int row, int column) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, column});

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            map[cur[0]][cur[1]] = count;
            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {

                int nextRow = cur[0] + dy[i];
                int nextColumn = cur[1] + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= N || map[nextRow][nextColumn] == 0) {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextColumn});
            }
        }
    }

    private static class Node implements Comparable<Node> {

        int row;
        int column;
        int count;

        public Node(int row, int column, int count) {
            this.row = row;
            this.column = column;
            this.count = count;
        }


        @Override
        public int compareTo(Node o) {
            return count - o.count;
        }
    }


}
