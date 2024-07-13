import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<List<Node>> map;
    static int A;
    static int B;
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); // KIST 위치
        B = Integer.parseInt(st.nextToken()); // 씨알푸드 위치

        int[] team = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            team[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            map.get(from).add(new Node(to, dist));
            map.get(to).add(new Node(from, dist));
        }

        int sum = 0;
        for (int idx : team) {
            sum += solve(idx);
        }
        System.out.println(sum);
    }

    private static int solve(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        int[] dp = new int[V + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (Node next : map.get(cur.idx)) {
                int nextDist = cur.dist + next.dist;
                if(dp[next.idx] < nextDist) {
                    continue;
                }
                dp[next.idx] = nextDist;
                pq.offer(new Node(next.idx, dp[next.idx]));
            }
        }

        dp[A] = (dp[A] == Integer.MAX_VALUE) ? -1 : dp[A];
        dp[B] = (dp[B] == Integer.MAX_VALUE) ? -1 : dp[B];
        return dp[A] + dp[B];
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int dist;

        public Node(int from, int dist) {
            this.idx = from;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }
}
