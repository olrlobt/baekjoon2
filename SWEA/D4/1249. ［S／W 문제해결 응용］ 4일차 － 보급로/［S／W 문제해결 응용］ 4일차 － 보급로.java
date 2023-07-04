
import java.util.Arrays;
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
                String input = sc.next();
                for (int column = 0; column < N; column++) {
                    map[row][column] = input.charAt(column) - '0';
                }
            }


            System.out.println("#" + testCase + " " + solve(map));
        }
    }

    private static int solve(int[][] map) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[map.length][map.length];

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (curNode.row == map.length - 1 && curNode.column == map.length - 1) {
                return curNode.count;
            }

            if (visited[curNode.row][curNode.column]) {
                continue;
            }
            visited[curNode.row][curNode.column] = true;

            for (int i = 0; i < 4; i++) {
                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map.length) {
                    continue;
                }

                pq.offer(new Node(nextRow, nextColumn, curNode.count + map[nextRow][nextColumn]));
            }
        }
        return 0;
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
