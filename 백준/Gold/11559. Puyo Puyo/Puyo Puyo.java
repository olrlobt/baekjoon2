import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static final int MAX_ROW = 12;
    static final int MAX_COLUMN = 6;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static List<LinkedList<Character>> map;

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
        System.out.println(solve());
    }

    private static int solve() {
        PriorityQueue<int[]> puyo = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int result = 0;

        while (true) {
            if (!isPuyo(puyo)) {
                break;
            }

            while (!puyo.isEmpty()) {
                int[] cur = puyo.poll();
                map.get(cur[1]).remove(cur[0]);
            }
            result++;
        }

        return result;
    }

    private static boolean isPuyo(PriorityQueue<int[]> puyo) {
        boolean[][] visited = new boolean[MAX_ROW][MAX_COLUMN];
        boolean isPuyo = false;

        for (int row = 0; row < MAX_ROW; row++) {
            for (int column = 0; column < MAX_COLUMN; column++) {

                if(map.get(column).size() <= row || map.get(column).get(row) == '.'){
                    continue;
                }

                List<int[]> count = bfs(row, column, visited);
                if (count.size() >= 4) {
                    isPuyo = true;
                    puyo.addAll(count);
                }
            }
        }
        return isPuyo;
    }

    private static List<int[]> bfs(int row, int column, boolean[][] visited) {

        Character color = map.get(column).get(row);
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> count = new ArrayList<>();

        queue.offer(new int[]{row, column});
        count.add(new int[]{row, column});
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
                count.add(new int[]{nextRow, nextColumn});
                queue.offer(new int[]{nextRow, nextColumn});
            }
        }
        return count;
    }
}
