
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> unit;
    static int M;
    static List<Integer> dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {
            int N = sc.nextInt();
            dp = new ArrayList<>();
            unit = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                unit.add(sc.nextInt());
            }

            M = sc.nextInt();
            solve();
        }
    }

    public static void solve() {

        dp.add(1);
        for (int i = 0; i < M; i++) {
            dp.add(0);
        }

        for (int index = 0; index < unit.size(); index++) {
            for (int i = unit.get(index); i <= M; i++) {
                dp.set(i, dp.get(i) + dp.get(i - unit.get(index)));
            }
        }
        System.out.println(dp.get(M));
    }
}
