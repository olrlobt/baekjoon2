
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int max = 0;
    static int L;
    static int W;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        W = sc.nextInt();

        map = new char[L][W];

        for (int length = 0; length < L; length++) {
            String input = sc.next();

            for (int width = 0; width < W; width++) {
                map[length][width] = input.charAt(width);
            }
        }

        for (int length = 0; length < L; length++) {
            for (int width = 0; width < W; width++) {

                if (map[length][width] == 'L') {
                    solve(width, length, 0);
                }
            }
        }
        System.out.println(max);
    }

    public static void solve(int x, int y, int distance) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, distance));
        visited = new boolean[L][W];

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (visited[curNode.y][curNode.x]) {
                continue;
            }
            visited[curNode.y][curNode.x] = true;
            max = Math.max(curNode.distance, max);

            for (int i = 0; i < 4; i++) {
                int nextX = curNode.x + dx[i];
                int nextY = curNode.y + dy[i];

                if (nextY < 0 || nextX < 0 || nextX >= W || nextY >= L || map[nextY][nextX] == 'W') {
                    continue;
                }

                queue.offer(new Node(nextX, nextY, curNode.distance + 1));
            }
        }

    }


    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }
    }
}
