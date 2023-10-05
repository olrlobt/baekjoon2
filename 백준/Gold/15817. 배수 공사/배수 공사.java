import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        Pipe[] pipes = new Pipe[N];

        for (int num = 0; num < N; num++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Pipe pipe = new Pipe(L, C);
            pipes[num] = pipe;

        }
        System.out.println(solve(pipes, X));
    }

    private static int solve(Pipe[] pipes, int x) {

        int[] dp = new int[x + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (Pipe pipe : pipes) {
            for (int len = x; len >= 0; len--) {
                for (int num = 1; num <= pipe.num && len - num * pipe.length >= 0; num++) {

                    if (dp[len - num * pipe.length] == -1) {
                        continue;
                    }
                    dp[len] += dp[len - num * pipe.length ] + 1;
                }
            }
        }
        return dp[x] + 1;
    }

    private static class Pipe {
        int length;
        int num;

        public Pipe(int length, int num) {
            this.length = length;
            this.num = num;
        }
    }


}
