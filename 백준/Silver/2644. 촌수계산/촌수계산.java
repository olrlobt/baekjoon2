
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> map = new ArrayList<>();
    static int one;
    static int two;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        one = Integer.parseInt(st.nextToken());
        two = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for (int num = 0; num < M; num++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map.get(start).add(end);
            map.get(end).add(start);
        }

        int solve = solve(one, 0);
        System.out.println(solve == 0 ? -1 : solve);
    }

    public static int solve(int cur, int count) {
        if (cur == two) {
            return count;
        }
        int result = 0;

        for (int next : map.get(cur)) {
            if(visited[next]){
                continue;
            }
            visited[next] = true;
            result += solve(next, count + 1);
        }
        return result;
    }
}
