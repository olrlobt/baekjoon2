
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N;
    static int K;
    static int M;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] horseX = {1, -1, 2, 2, 1, -1, -2, -2};
    static int[] horseY = {-2, -2, -1, 1, 2, 2, 1, -1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        M = sc.nextInt();
        N = sc.nextInt();


        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        System.out.println(solve());
    }

    public static int solve() {

        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(0, 0, 0, K));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (visited[curNode.row][curNode.column][curNode.K]) {
                continue;
            }
            visited[curNode.row][curNode.column][curNode.K] = true;

            if (curNode.row == N - 1 && curNode.column == M - 1) {
                return curNode.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= M || map[nextRow][nextColumn] == 1) {
                    continue;
                }

                queue.offer(new Node(nextRow, nextColumn, curNode.count + 1, curNode.K));
            }

            if (curNode.K > 0) {
                for (int i = 0; i < 8; i++) {
                    int nextRow = curNode.row + horseY[i];
                    int nextColumn = curNode.column + horseX[i];

                    if (nextRow < 0 || nextColumn < 0 || nextRow >= N || nextColumn >= M
                            || map[nextRow][nextColumn] == 1) {
                        continue;
                    }

                    queue.offer(new Node(nextRow, nextColumn, curNode.count + 1, curNode.K - 1));

                }
            }
        }
        return -1;
    }

    private static class Node {

        int row;
        int column;
        int count;
        int K;

        public Node(int row, int column, int count, int k) {
            this.row = row;
            this.column = column;
            this.count = count;
            this.K = k;
        }
    }
}
