import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] visited;
    static boolean[][][][] road;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 1;
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new int[N][N];
        road = new boolean[N][N][N][N];

        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            road[r][c][r2][c2] = true;
            road[r2][c2][r][c] = true;
        }

        int partNum = divideLocation();
        int[] part = new int[partNum];

        for (int cow = 0; cow < K; cow++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            part[visited[r][c]]++;
        }

        int result = K * (K - 1)  ; // 전체 경우
        int reduce = Arrays.stream(part)
                .filter(val -> val > 1)
                .map(val -> val * (val - 1)  )
                .reduce(0, Integer::sum);


//        for (int row = 0; row < map.length; row++) {
//            System.out.println(Arrays.toString(visited[row]));
//        }

//        System.out.println("Arrays.toString(part) = " + Arrays.toString(part));
//        System.out.println("result = " + result);
        System.out.println((result - reduce)/2);
    }

    private static int divideLocation() {
        int sort = 1;

        for (int row = 1; row < map.length; row++) {
            for (int column = 1; column < map.length; column++) {
                if (visited[row][column] != 0) {
                    continue;
                }
                visited[row][column] = sort;
                solve(row, column, sort++);
            }
        }

        return sort;
    }

    private static void solve(int row, int column, int sort) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(row, column));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = curNode.row + dy[dir];
                int nextColumn = curNode.column + dx[dir];

                if (nextRow < 1 || nextColumn < 1 || nextRow >= map.length || nextColumn >= map.length
                        || visited[nextRow][nextColumn] != 0 || road[curNode.row][curNode.column][nextRow][nextColumn]) {
                    continue;
                }
                visited[nextRow][nextColumn] = sort;
                queue.offer(new Node(nextRow, nextColumn));
            }
        }
    }

    private static class Node {
        int row;
        int column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}