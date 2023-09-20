import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static final int MAX_ROW = 12;
    static final int MAX_COLUMN = 6;
    static final int[] COLOR = {'R', 'G', 'B', 'P', 'Y'};

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static List<LinkedList<Character>> map;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new ArrayList<>(MAX_COLUMN);

        for (int column = 0; column < MAX_COLUMN; column++) {
            map.add(new LinkedList<>());
        }

        for (int row = 0; row < MAX_ROW; row++) {
            String input = br.readLine();
            for (int column = 0; column < MAX_COLUMN; column++) {
                map.get(column).addFirst(input.charAt(column));
            }
        }

        solve();
        System.out.println(result);
    }

    private static void solve() {

        boolean[][] puyo = new boolean[MAX_ROW][MAX_COLUMN];

        while (true) {
            boolean loop = false;
            for (int row = 0; row < MAX_ROW; row++) {
                for (int column = 0; column < MAX_COLUMN; column++) {

                    if (row >= map.get(column).size() || !isCOLOR(row, column)) {
                        continue;
                    }
                    if (bfs(map.get(column).get(row), row, column, puyo)) {
                        loop = true;
                    }
                }
            }
            if(!loop){
                break;
            }

            for (int col = 0; col < MAX_COLUMN; col++) {
                for (int ro = MAX_ROW - 1; ro >= 0; ro--) {
                    if (!puyo[ro][col]) {
                        continue;
                    }
                    puyo[ro][col] = false;
                    map.get(col).remove(ro);
                }
            }
            result++;
        }


    }

    private static boolean bfs(char color, int row, int column, boolean[][] puyo) {

        int count = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, column});

        boolean[][] visited = new boolean[MAX_ROW][MAX_COLUMN];
        visited[row][column] = true;

        while (!queue.isEmpty()) {

            int[] next = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = next[0] + dy[i];
                int nextColumn = next[1] + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= MAX_ROW || nextColumn >= MAX_COLUMN
                        || nextRow >= map.get(nextColumn).size() || map.get(nextColumn).get(nextRow) != color
                        || visited[nextRow][nextColumn]) {
                    continue;
                }
                visited[nextRow][nextColumn] = true;

                count++;
                queue.offer(new int[]{nextRow, nextColumn});
            }
        }

        if (count >= 4) {
            for (int col = column; col < MAX_COLUMN; col++) {
                for (int ro = MAX_ROW - 1; ro >= row; ro--) {
                    if (!visited[ro][col]) {
                        continue;
                    }
                    puyo[ro][col] = true;
                }
            }
            return true;
        }
        return false;
    }


    private static boolean isCOLOR(int row, int column) {
        return Arrays.stream(COLOR).anyMatch(color -> color == map.get(column).get(row));
    }
}
