
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // node
        int R = sc.nextInt(); // root
        int Q = sc.nextInt(); // query

        visited = new int[N+1];

        for (int node = 0; node <= N; node++) {
            map.add(new ArrayList<>());
        }

        for (int node = 0; node < N -1; node++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            map.get(start).add(end);
            map.get(end).add(start);
        }
        solve(R);

        for (int node = 0; node < Q; node++) {
            System.out.println(visited[sc.nextInt()]);
        }
    }

    public static int solve(int root) {
        visited[root] = 1;

        for (int child : map.get(root)) {
            if(visited[child] != 0){
                continue;
            }
            visited[root] += solve(child);
        }

        return visited[root];
    }
}
