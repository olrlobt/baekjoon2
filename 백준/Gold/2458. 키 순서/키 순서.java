import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> big = new ArrayList<>(); // 자기보다 큰 쪽
    static List<List<Integer>> small = new ArrayList<>(); // 자기보다 작은 쪽
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            big.add(new ArrayList<>());
            small.add(new ArrayList<>());
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            big.get(first).add(last);
            small.get(last).add(first);
        }
        int result = 0;
        for (int idx = 1; idx <= N; idx++) {
            int count = 0;

            count += Count(idx, big);
            count += Count(idx, small);
            Arrays.fill(visited, false);
            if (count == N + 1) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static int Count(int idx, List<List<Integer>> relation) {
        int count = 1;
        for (int next : relation.get(idx)) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            count += Count(next, relation);
        }
        return count;
    }
}
