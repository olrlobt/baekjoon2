
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> dp = new ArrayList<>();
    static List<int[]> things = new ArrayList<>();
    static int K;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        for (int testCase = 0; testCase < N; testCase++) {
            int W = sc.nextInt();
            int V = sc.nextInt();

            int[] P = {W, V};
            things.add(P);
        }

        solve();
    }

    public static void solve() {


        for (int i = 0; i < K + 1; i++) {
            dp.add(0);
        }
        List<Integer> newDp = new ArrayList<>(dp);

        for (int index = 0; index < N; index++) {


            for (int i = things.get(index)[0]; i < K+1; i++) {
                int Q = i - things.get(index)[0];
                if (dp.get(i) < things.get(index)[1] + dp.get(Q)) {
                    newDp.set(i, things.get(index)[1] + dp.get(Q));
                }
            }

            dp = new ArrayList<>(newDp);

        }
        System.out.println(dp.get(K));

    }
}
