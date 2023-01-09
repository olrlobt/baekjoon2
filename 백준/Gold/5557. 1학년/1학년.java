import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;
    static List<Integer> P = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            P.add(sc.nextInt());
        }

        solve();
    }

    public static void solve() {

        long[][] dp = new long[N][21];
        dp[0][P.get(0)] = 1;
        
        for (int index = 1; index < P.size(); index++) {
            for (int i = 0; i <= 20; i++) {

                if (dp[index - 1][i] == 0) {
                    continue;
                }

                if (i - P.get(index) >= 0) {
                    dp[index][i - P.get(index)] += dp[index - 1][i];
                }
                if (i + P.get(index) <= 20) {
                    dp[index][i + P.get(index)] += dp[index - 1][i];
                }

            }
        }

        System.out.println(dp[P.size()-2][P.get(P.size()-1)]);
    }
}
