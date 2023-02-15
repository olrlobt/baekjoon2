
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int node = 1; node < N; node++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            graph.get(start).add(new Node(end, distance));
            graph.get(end).add(new Node(start, distance));
        }

        for (int node = 0; node < M; node++) {
            solve(sc.nextInt(), sc.nextInt());
        }
    }

    public static void solve(int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (curNode.index == end) {
                System.out.println(curNode.distance);
                return;
            }

            if (visited[curNode.index]) {
                continue;
            }
            visited[curNode.index] = true;

            for (Node node : graph.get(curNode.index)) {
                queue.offer(new Node(node.index, curNode.distance + node.distance));
            }

        }


    }

    private static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
