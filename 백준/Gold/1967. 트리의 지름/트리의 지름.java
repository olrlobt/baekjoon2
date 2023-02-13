
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int N;
    static boolean[] visited;
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        boolean []child = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int line = 1; line < N; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            tree.get(start).add(new Node(end, distance));
            tree.get(end).add(new Node(start, distance));

            child[start] = true;
        }

        for (int i = 1; i <= N; i++) {
            if(child[i]){
                continue;
            }

            visited = new boolean[N + 1];
            visited[i] = true;
            solve(i,0);
        }
        System.out.println(max);
    }

    public static void solve(int start, int distance) {

        if(max < distance){
            max = distance;
        }

        for(Node node : tree.get(start)){
            if(visited[node.index]){
                continue;
            }
            visited[node.index] = true;
            solve(node.index , node.distance + distance);
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
