import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int minCount = 100_000;
    static boolean[][][] visited = new boolean[61][61][61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scv = new int[3];
        for (int idx = 0; idx < N; idx++) {
            scv[idx] = Integer.parseInt(st.nextToken());
        }
        solve(scv, 0);
        System.out.println(minCount);
    }

    private static void solve(int[] scv, int count) {

        Arrays.sort(scv);

        if (count >= minCount || visited[Math.max(scv[0], 0)][Math.max(scv[1], 0)][Math.max(scv[2], 0)]) {
            return;
        }else if(Arrays.stream(scv).noneMatch(value -> value > 0)){
            minCount = count;
            return;
        }
        visited[Math.max(scv[0], 0)][Math.max(scv[1], 0)][Math.max(scv[2], 0)] = true;

        solve(new int[]{scv[0] - 1, scv[1] - 3, scv[2] - 9}, count + 1);
        solve(new int[]{scv[0] - 3, scv[1] - 1, scv[2] - 9}, count + 1);

        if (scv[1] <= 0) {
            return;
        }

        solve(new int[]{scv[0] - 1, scv[1] - 9, scv[2] - 3}, count + 1);
        solve(new int[]{scv[0] - 3, scv[1] - 9, scv[2] - 1}, count + 1);

        if (scv[0] <= 0) {
            return;
        }

        solve(new int[]{scv[0] - 9, scv[1] - 1, scv[2] - 3}, count + 1);
        solve(new int[]{scv[0] - 9, scv[1] - 3, scv[2] - 1}, count + 1);
    }

}