
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();

        int[] sight = new int[N];

        for (int index = 0; index < N; index++) {
            sight[index] = sc.nextInt();
            map.add(new ArrayList<>());
        }

        for (int route = 0; route < M; route++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            long distance = sc.nextLong();

            if (start != N - 1 && end != N - 1) {
                if (sight[start] == 1 || sight[end] == 1) {
                    continue;
                }
            }

            map.get(start).add(new Node(end, distance));
            map.get(end).add(new Node(start, distance));
        }

        System.out.println(solve());
    }

    public static long solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        boolean[] visited = new boolean[N];
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (visited[curNode.index]) {
                continue;
            }
            visited[curNode.index] = true;

            if (curNode.index == N - 1) {
                return curNode.distance;
            }

            for (Node node : map.get(curNode.index)) {

                if (dist[node.index] > curNode.distance + node.distance) {
                    dist[node.index] = curNode.distance + node.distance;
                    pq.offer(new Node(node.index, curNode.distance + node.distance));
                }

            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node> {
        int index;
        long distance;

        public Node(int index, long distance) {
            this.index = index;
            this.distance = distance;
        }


        @Override
        public int compareTo(Node o) {
            return (int) (distance - o.distance);
        }
    }
}
