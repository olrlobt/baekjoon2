
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int E;

    static int V1;
    static int V2;

    static final int INF = Integer.MAX_VALUE;

    static ArrayList<ArrayList<Node>> gragh = new ArrayList<>();
    static int[] distanceDP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        E = sc.nextInt();

        for (int i = 0 ; i <= N ; i ++){
            gragh.add(new ArrayList<>());
        }

        for (int line = 0; line < E; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            gragh.get(start).add(new Node(end,distance));
            gragh.get(end).add(new Node(start,distance));
        }
        V1 = sc.nextInt();
        V2 = sc.nextInt();


        getResult();


    }

    private static void getResult() {

        solve(1);
        int result1 = distanceDP[V1];
        int result2 = distanceDP[V2];
        boolean check = false;

        if(distanceDP[V1] == INF || distanceDP[V2] == INF){
            check = true;
        }


        solve(V1);
        result1 += distanceDP[V2];
        result2 += distanceDP[N];

        if(distanceDP[N] == INF || distanceDP[V2] == INF){
            check = true;
        }

        solve(V2);
        result2 += distanceDP[V1];
        result1 += distanceDP[N];
        if(distanceDP[N] == INF || distanceDP[V1] == INF){
            check = true;
        }

        if(check){
            System.out.println(-1);
            return;
        }

        System.out.println(result1 > result2 ? result2 : result1);



    }

    public static void solve(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        distanceDP = new int[N + 1];
        Arrays.fill(distanceDP,INF);
        distanceDP[start] = 0;
        pq.add(new Node(start,0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int endIndex = curNode.endIndex;

            for (Node node : gragh.get(endIndex)) {

                if(distanceDP[node.endIndex] > node.distance + distanceDP[endIndex] ){
                    distanceDP[node.endIndex] = node.distance + distanceDP[endIndex];
                    pq.add(new Node(node.endIndex, distanceDP[node.endIndex]));
                }

            }
        }
    }

    public static class Node implements Comparable<Node>{
        int endIndex;
        int distance;

        public Node(int endIndex, int distance) {
            this.endIndex = endIndex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }
    }
}
