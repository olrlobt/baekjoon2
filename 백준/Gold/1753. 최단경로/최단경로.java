import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine()); // 시작

        ArrayList<ArrayList<Node>> map = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map.get(u).add(new Node(v, w));
        }

        int[] result = solve(map, K);

        for (int i = 1; i < result.length; i ++) {
            if (result[i] == -1) {
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] solve(ArrayList<ArrayList<Node>> map, int k) {

        int[] dp = new int[map.size()];
        Arrays.fill(dp, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (dp[curNode.index] != -1) {
                continue;
            }
            dp[curNode.index] = curNode.dis;


            for (Node nextNode : map.get(curNode.index)) {
                pq.offer(new Node(nextNode.index, curNode.dis + nextNode.dis));
            }
        }

        return dp;
    }


    private static class Node implements Comparable<Node> {

        int index;
        int dis;

        public Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(dis, o.dis);
        }
    }



}
