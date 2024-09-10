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
            }else{
                for (int col = 1; col <= maxCol; col++) {
                    calCol(col);
                }
            }
        }

        if (count == 101) {
            System.out.println(-1);
        }else{
            System.out.println(count);
        }
    }


    // 수의 등장 횟수가 커지는 순으로
    // 수가 커지는 순
    private static void calRow(int[] row) {
        int[] count = new int[101];

        for (int num : row) {
            count[num]++;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int idx = 1; idx < 101; idx++) {
            if (count[idx] != 0) {
                pq.offer(new Node(idx, count[idx]));
            }
        }

        Arrays.fill(row, 0);
        maxCol = Math.max(maxCol, pq.size() * 2);
        for (int idx = 1; idx < 50 && !pq.isEmpty(); idx++) {
            Node cur = pq.poll();
            row[idx * 2 - 1] = cur.idx;
            row[idx * 2] = cur.count;
        }
    }

    // 수의 등장 횟수가 커지는 순으로
    // 수가 커지는 순
    private static void calCol(int colIdx) {
        int[] count = new int[101];

        for (int row = 0; row <= maxRow; row++) {
            count[map[row][colIdx]]++;
            map[row][colIdx] = 0;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int idx = 1; idx < 101; idx++) {
            if (count[idx] != 0) {
                pq.offer(new Node(idx, count[idx]));
            }
        }

        maxRow = Math.max(maxRow, pq.size() * 2);
        for (int idx = 1; idx < 50 && !pq.isEmpty(); idx++) {
            Node cur = pq.poll();
            map[idx * 2 - 1][colIdx] = cur.idx;
            map[idx * 2][colIdx] = cur.count;
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
                return idx - o.idx;
            }
            return count - o.count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", count=" + count +
                    '}';
        }
    }

}
