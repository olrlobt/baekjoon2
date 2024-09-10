import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int maxRow = 3;
    static int maxCol = 3;
    static int[][] map = new int[101][101];
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] count = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int row = 1; row <= 3; row++) {
            st = new StringTokenizer(br.readLine());

            map[row][1] = Integer.parseInt(st.nextToken());
            map[row][2] = Integer.parseInt(st.nextToken());
            map[row][3] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        while (map[R][C] != K && count++ < 100) {

            if (maxRow >= maxCol) {
                for (int row = 1; row <= maxRow; row++) {
                    calRow(map[row]);
                }
            } else {
                for (int col = 1; col <= maxCol; col++) {
                    calCol(col);
                }
            }
        }

        if (count == 101) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    private static void calRow(int[] row) {
        Arrays.fill(count, 0);
        pq.clear();

        for (int col = 1; col <= maxCol; col++) {
            count[row[col]]++;
        }
                
        for (int idx = 1; idx < 101; idx++) {
            if (count[idx] != 0) {
                pq.offer(new Node(idx, count[idx]));
            }
        }

        Arrays.fill(row, 0);
        maxCol = Math.max(maxCol, pq.size() * 2);
        for (int idx = 2; idx < 100 && !pq.isEmpty(); idx += 2) {
            Node cur = pq.poll();
            row[idx - 1] = cur.idx;
            row[idx] = cur.count;
        }
    }

    private static void calCol(int colIdx) {
        Arrays.fill(count, 0);
        pq.clear();

        for (int row = 1; row <= maxRow; row++) {
            count[map[row][colIdx]]++;
            map[row][colIdx] = 0;
        }

        int max = maxRow;

        for (int idx = 1; idx < 101; idx++) {
            if (count[idx] != 0) {
                pq.offer(new Node(idx, count[idx]));
            }
        }

        maxRow = Math.max(maxRow, pq.size() * 2);
        for (int idx = 2; idx < 100 && !pq.isEmpty(); idx += 2) {
            Node cur = pq.poll();
            map[idx - 1][colIdx] = cur.idx;
            map[idx][colIdx] = cur.count;
        }
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int count;

        public Node(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            if (this.count == o.count) {
                return Integer.compare(idx, o.idx);
            }
            return Integer.compare(count, o.count);
        }
    }
}
