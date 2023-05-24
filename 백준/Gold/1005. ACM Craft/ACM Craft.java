
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] building;
    static ArrayList<ArrayList<Integer>> map;
    static int end;

    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            boolean[] start = new boolean[N + 1];

            building = new int[N + 1];
            dp = new int[N + 1];
            Arrays.fill(dp, -1);
            map = new ArrayList<>();

            map.add(new ArrayList<>());
            for (int i = 1; i <= N; i++) {
                building[i] = sc.nextInt();
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                int startIndex = sc.nextInt();
                int endIndex = sc.nextInt();
                map.get(endIndex).add(startIndex);

                start[endIndex] = true;
            }
            end = sc.nextInt();

            solve(end);
            System.out.println(dp[end]);
        }

    }

    public static int solve(int index) {

        if(dp[index] != -1){
            return dp[index];
        }

        dp[index] = building[index];
        int max = dp[index];

        for (int nextIndex : map.get(index)) {

            max = Math.max(solve(nextIndex) + dp[index], max);
        }
        dp[index] = max;

        return dp[index];
    }
}
