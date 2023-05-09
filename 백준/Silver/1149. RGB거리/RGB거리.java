
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static int[][] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][3];
        memo = new int[N][3];

        for (int home = 0; home < N; home++) {
            map[home][0] = sc.nextInt();
            map[home][1] = sc.nextInt();
            map[home][2] = sc.nextInt();
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
