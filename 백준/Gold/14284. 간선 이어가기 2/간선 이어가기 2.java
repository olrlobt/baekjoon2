
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int S;
    static int T;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static int[] distanceDP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int line = 0; line < M; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            graph.get(start).add(new Node(end, distance));
            graph.get(end).add(new Node(start, distance));
        }
        S = sc.nextInt();
        T = sc.nextInt();


        solve();
        System.out.println(distanceDP[T]);
    }

    public static void solve() {
        distanceDP = new int[N + 1];
        Arrays.fill(distanceDP, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distanceDP[S] = 0;
        pq.offer(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int index = node.index;
            int distance = node.distance;

            if (distanceDP[index] < distance) {
                continue;
            }

            for (int i = 0; i < graph.get(index).size(); i++) {
                int cost = graph.get(index).get(i).getDistance() + distanceDP[index];

                if (distanceDP[graph.get(index).get(i).getIndex()] > cost) {
                    distanceDP[graph.get(index).get(i).getIndex()] = cost;
                    pq.add(new Node(graph.get(index).get(i).getIndex(), cost));
                }
            }
        }
    }


    public static class Node implements Comparable<Node> {

        int index;
        int distance;

        public int getDistance() {
            return distance;
        }

        public int getIndex() {
            return index;
        }

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance < o.distance) {
                return -1;
            }
            return 1;
        }
    }
}
