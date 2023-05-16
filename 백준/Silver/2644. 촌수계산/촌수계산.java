import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int one;
    static int two;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        
        one = sc.nextInt();
        two = sc.nextInt();

        int M = sc.nextInt();
        for (int num = 0; num < M; num++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            map.get(start).add(end);
            map.get(end).add(start);
        }

        System.out.println(solve(one, 0));
    }

    public static int solve(int cur, int count) {

        if (cur == two) {
            return count;
        }

        for (int next : map.get(cur)) {
            if(visited[next]){
                continue;
            }
            visited[next] = true;

            int result = solve(next, count + 1);
            if (result != -1){
                return result;
            };
        }
        return -1;
    }
}
