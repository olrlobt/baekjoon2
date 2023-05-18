
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int[] visited;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        visited = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int num = 0; num < N; num++) {
            int nodeIndex = sc.nextInt();

            while (true) {
                int index = sc.nextInt();

                if (index == -1) {
                    break;
                }
                int distance = sc.nextInt();

                Node node = new Node(index, distance);
                map.get(nodeIndex).add(node);

            }
        }

        Node startNode = bfs(new Node(1,0));

        Node endNode = bfs(startNode);
        System.out.println(endNode.distance);
    }

    public static Node bfs(Node startNode) {
        Node maxNode = new Node(0,0);
        int[] dp = new int[N + 1];
        dp[startNode.index] = 0;
        Arrays.fill(dp,-1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startNode.index, 0));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if(dp[curNode.index] != -1){
                continue;
            }
            dp[curNode.index] = curNode.distance;
            if(maxNode.distance < curNode.distance){
                maxNode = curNode;
            }

            for (Node nextNode : map.get(curNode.index)) {

                queue.offer(new Node(nextNode.index, nextNode.distance + curNode.distance));
            }
        }
        return maxNode;
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
            return distance - o.distance;
        }
    }
}
