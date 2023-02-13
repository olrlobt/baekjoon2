
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> graph;
    static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();

        for (int testCase = 0; testCase < K; testCase++) {

            int V = sc.nextInt();
            int E = sc.nextInt();

            graph = new ArrayList<>();
            visited = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int line = 0; line < E; line++) {
                int start = sc.nextInt();
                int end = sc.nextInt();

                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            boolean result = false;
            for(int i = 1 ; i <= V; i ++){

                if(visited[i] == 0){
                    result = solve(i);
                }

                if(!result){
                    break;
                }
            }

            if(result){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }


        }
    }

    public static boolean solve(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {

            int curNode = queue.poll();

            for (int node : graph.get(curNode)) {

                if (visited[node] != 0) {
                    if ((visited[node] - visited[curNode]) % 2 == 0) {
                        return false;
                    }
                    continue;
                }

                visited[node] = visited[curNode] + 1;
                queue.offer(node);
            }

        }
        return true;
    }
}
