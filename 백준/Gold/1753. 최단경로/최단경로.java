import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int V;
    static int E;
    static int K;
    static int[] distanceDP;
    static final int INF = Integer.MAX_VALUE;

    static ArrayList<ArrayList<Node>> gragh = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        K = sc.nextInt();

        distanceDP = new int[V + 1];
        Arrays.fill(distanceDP, INF);
        for (int i = 0; i <= V; i++) {
            gragh.add(new ArrayList<>());
        }

        for (int line = 0; line < E; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            gragh.get(start).add(new Node(end, distance));
        }

        solve();

        for (int i = 1; i <= V; i++) {
            if (distanceDP[i] == INF) {
                System.out.println("INF");
                continue;
            }
            System.out.println(distanceDP[i]);
        }

    }

    public static void solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[V + 1];
        pq.add(new Node(K, 0));
        distanceDP[K] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int index = curNode.index;

            if (check[index]) {
                continue;
            }
            check[index] = true;

            for(Node node : gragh.get(index)){
                int cost = node.distance;

                if (distanceDP[node.index] > cost + distanceDP[index]) {
                    distanceDP[node.index] = cost + distanceDP[index];
                    pq.add(new Node(node.index, distanceDP[node.index]));
                }

            }
        }
    }

    private static class Node implements Comparable<Node> {

        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance > o.distance) {
                return 1;
            }
            return -1;
        }
    }
}
