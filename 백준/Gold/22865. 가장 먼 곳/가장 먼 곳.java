import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] houses = new int[3]; // A, B, C

        for (int idx = 0; idx < 3; idx++) {
            houses[idx] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            list.get(to).add(new Node(from, dis));
            list.get(from).add(new Node(to, dis));
        }

        System.out.println(solve(list, houses, N));
    }

    private static int solve(List<List<Node>> list, int[] houses, int N) {
        long[][] dp = new long[3][N + 1];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        for (int idx = 0; idx < 3; idx++) {
            dijkstra(list, houses[idx], dp[idx]);
        }

        long maxValue = -1;
        int maxIdx = 1;

        for (int i = 1; i <= N; i++) {
            long minDistance = Long.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                minDistance = Math.min(minDistance, dp[j][i]);
            }
            if (minDistance > maxValue) {
                maxValue = minDistance;
                maxIdx = i;
            } else if (minDistance == maxValue) {
                if (i < maxIdx) {
                    maxIdx = i;
                }
            }
        }

        return maxIdx;
    }

    private static void dijkstra(List<List<Node>> list, int start, long[] distances) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.from;
            long dis = cur.dis;

            if (distances[from] < dis) continue;

            for (Node nextNode : list.get(from)) {
                if (distances[nextNode.from] > dis + nextNode.dis) {
                    distances[nextNode.from] = dis + nextNode.dis;
                    pq.offer(new Node(nextNode.from, distances[nextNode.from]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int from;
        long dis;

        public Node(int from, long dis) {
            this.from = from;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(dis, o.dis);
        }
    }
}
