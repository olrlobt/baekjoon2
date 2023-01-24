
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static final int INF = Integer.MAX_VALUE;

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        dp = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int shedInfo = 0; shedInfo < M; shedInfo++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int fodder = sc.nextInt();

            graph.get(start).add(new Node(end, fodder));
            graph.get(end).add(new Node(start, fodder));
        }

        Arrays.fill(dp,INF);

        solve();
        System.out.println(dp[N]);
    }

    public static void solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1,0));

        dp[1] = 0 ;


        while (!pq.isEmpty()){
            Node node = pq.poll();
            int index = node.index;
            int distance = node.distance;

            if(dp[index] < distance){
                continue;
            }

            for(int i = 0; i < graph.get(index).size(); i ++){
                int cost = dp[index] + graph.get(index).get(i).getDistance();

                if(cost < dp[graph.get(index).get(i).getIndex()]){
                    dp[graph.get(index).get(i).getIndex()] = cost;
                    pq.add(new Node(graph.get(index).get(i).getIndex() , cost ));
                }
            }
        }

    }


    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public int getIndex() {
            return index;
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
