import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int K;
    static int N;
    static List<Integer> dp = new ArrayList<>();
    static int[] P;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        P = new int[N];
        for (int testCase = 0; testCase < N; testCase++) {
            P[testCase] = sc.nextInt();
        }

        solve();
    }

    public static void solve() {

        dp.add(0);
        for (int i = 0; i < K; i++) {
            dp.add(0);
        }

        dp.set(0,1);
        for (int index = 0; index < N; index++) {
            for (int j = P[index]; j <= K; j++) {
                dp.set(j, dp.get(j) + dp.get(j - P[index]));
            }
        }

        System.out.println(dp.get(K));
    }
}
