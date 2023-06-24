
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] schedule = new int[N][2];

        for (int day = 0; day < N; day++) {
            schedule[day][0] = sc.nextInt();
            schedule[day][1] = sc.nextInt();
        }

        solve(schedule);
    }

    public static void solve(int[][] schedule) {

        int[] dp = new int[schedule.length+1];

        for (int i = dp.length - 2; i >= 0; i--) {

            if (schedule[i][0] + i >= dp.length) {
                dp[i] = dp[i+1];
                continue;
            }
            dp[i] = Math.max(dp[i + schedule[i][0]] + schedule[i][1], dp[i + 1]);

        }
        System.out.println(dp[0]);
    }
}
