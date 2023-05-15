
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        solve();
    }

    public static void solve() {

        Queue<int[]> queue = new LinkedList<>();
        int[] dp = new int[100_001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int count = 0;
        int result = 0;
        queue.offer(new int[]{M, 0});

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (cur[0] == N) {
                if (result == 0) {
                    result = cur[1];
                }

                if (result == cur[1]) {
                    count++;
                }
                continue;
            }

            if (dp[cur[0]] < cur[1]) {
                continue;
            }
            dp[cur[0]] = cur[1];

            if (cur[0] % 2 == 0) {
                queue.offer(new int[]{cur[0] / 2, cur[1] + 1});
            }
            if (0 < cur[0]) {
                queue.offer(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if(cur[0] < 100000){
                queue.offer(new int[]{cur[0] + 1, cur[1] + 1});
            }

        }

        System.out.println(result);
        System.out.println(count);


    }
}
