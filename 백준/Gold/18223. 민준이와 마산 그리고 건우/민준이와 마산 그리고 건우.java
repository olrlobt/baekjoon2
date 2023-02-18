
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int V;
    static int E;
    static int P;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        P = sc.nextInt();

        for (int i = 0; i <= V; i++) {
            map.add(new ArrayList<>());
        }

        for (int route = 0; route < E; route++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            map.get(start).add(new Node(end, distance, false));
            map.get(end).add(new Node(start, distance, false));
        }

        if(solve()){
            System.out.println("SAVE HIM");
        }else {

            System.out.println("GOOD BYE");
        }
    }

    public static boolean solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V+1];
        pq.offer(new Node(1, 0, false));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(visited[curNode.index]){
                continue;
            }
            visited[curNode.index] = true;


            if(curNode.index == P){
                curNode.save = true;
            }

            if (curNode.index == V) {
                return curNode.save;
            }

            for (Node node : map.get(curNode.index)) {
                pq.offer(new Node(node.index, node.distance + curNode.distance, curNode.save));
            }
        }
        return false;
    }

    private static class Node implements Comparable<Node> {
        int index;
        int distance;
        boolean save;

        public Node(int index, int distance, boolean save) {
            this.index = index;
            this.distance = distance;
            this.save = save;
        }

        @Override
        public int compareTo(Node o) {
            if (distance == o.distance) {
                if(o.save){
                    return 1;
                }else if(save){
                    return -1;
                }
            }
            return distance - o.distance;
        }
    }
}
