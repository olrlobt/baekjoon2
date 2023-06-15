
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int R;
    static int C;
    static Character[][] map;
    static int[] start = new int[2];
    static Queue<Node> queue = new LinkedList<>();
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        map = new Character[R][C];

        for (int row = 0; row < R; row++) {
            String input = sc.next();
            for (int column = 0; column < C; column++) {
                map[row][column] = input.charAt(column);

                if (map[row][column] == 'J') {
                    start = new int[]{row, column};
                } else if (map[row][column] == 'F') {
                    queue.offer(new Node(row, column, 1));
                }

            }
        }

        int solve = solve();

        if(solve == -1){
            System.out.println("IMPOSSIBLE");
        }else {
            System.out.println(solve);
        }


    }

    public static int solve() {

        int[][] visited = new int[R][C];

        while (!queue.isEmpty()) {

            Node curFire = queue.poll();

            if (visited[curFire.row][curFire.column] != 0) {
                continue;
            }
            visited[curFire.row][curFire.column] = curFire.count;

            for (int i = 0; i < 4; i++) {

                int nextRow = curFire.row + dy[i];
                int nextColumn = curFire.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= R || nextColumn >= C
                        || map[nextRow][nextColumn] == '#') {
                    continue;
                }

                queue.offer(new Node(nextRow, nextColumn, curFire.count + 1));
            }
        }

        queue.offer(new Node(start[0], start[1], 1));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (visited[curNode.row][curNode.column] != 0 && visited[curNode.row][curNode.column] <= curNode.count) {
                continue;
            }
            visited[curNode.row][curNode.column] = curNode.count;

            if (curNode.row == 0 || curNode.row == R - 1 || curNode.column == 0 || curNode.column == C - 1) {
                return curNode.count;
            }

            for (int i = 0; i < 4; i++) {

                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (map[nextRow][nextColumn] == '.') {
                    queue.offer(new Node(nextRow, nextColumn, curNode.count + 1));
                }
            }
        }

        return -1;
    }

    private static class Node {

        int row;
        int column;
        int count;

        public Node(int row, int column, int count) {
            this.row = row;
            this.column = column;
            this.count = count;
        }
    }
}
