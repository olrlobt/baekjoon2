import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N + 1][N + 1];

        for (int row = 1; row < map.length; row++) {
            Arrays.fill(map[row], 51);
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1) {
                break;
            }

            map[a][b] = map[b][a] = 1;
        }

        Queue<Integer> solve = solve(map);

        sb.append(map[solve.peek()][0]).append(" ").append(solve.size()).append("\n");
        while (!solve.isEmpty()) {
            sb.append(solve.poll()).append(" ");
        }
        System.out.println(sb);
    }

    private static Queue<Integer> solve(int[][] map) {

        for (int k = 1; k < map.length; k++) {
            map[k][k] = 0;
            map[k][0] = 0;
            for (int row = 1; row < map.length; row++) {
                if (k == row) {
                    continue;
                }
                for (int column = 1; column < map.length; column++) {
                    if (row == column || k == column) {
                        continue;
                    }
                    map[row][column] = Math.min(map[row][column], map[row][k] + map[k][column]);
                }
            }
        }

        int min = 51;

        Queue<Integer> pq = new ArrayDeque<>();

        for (int row = 1; row < map.length; row++) {
            map[row][0] = Arrays.stream(map[row]).max().getAsInt();

            if(min < map[row][0]){
                continue;
            } else if (min > map[row][0]) {
                pq.clear();
                min = map[row][0];
                pq.offer(row);
                continue;
            }
            pq.offer(row);
        }

        return pq;
    }
}
