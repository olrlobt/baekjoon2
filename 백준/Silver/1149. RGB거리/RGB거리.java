import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        memo = new int[N][3];

        for (int home = 0; home < N; home++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[home][0] = Integer.parseInt(st.nextToken());
            map[home][1] = Integer.parseInt(st.nextToken());
            map[home][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Math.min(solve(0, 2), Math.min(solve(0, 0), solve(0, 1))));
    }

    public static int solve(int count, int choose) {

        if (count == N) {
            return 0;
        }

        if (memo[count][choose] != 0) {
            return memo[count][choose];
        }

        int cost = map[count][choose];

        if (choose == 0) {
            cost += Math.min(solve(count + 1, 1), solve(count + 1, 2));
        } else if (choose == 1) {
            cost += Math.min(solve(count + 1, 0), solve(count + 1, 2));
        } else {
            cost += Math.min(solve(count + 1, 0), solve(count + 1, 1));
        }
        memo[count][choose] = cost;

        return cost;
    }
}
