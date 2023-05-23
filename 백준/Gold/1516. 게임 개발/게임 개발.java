
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int N;
    static int[] dp;
    static int[] buildings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        buildings = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
        }

        for (int num = 0; num < N; num++) {
            int time = sc.nextInt();
            buildings[num] = time;

            int building = sc.nextInt();

            while (building != -1) {
                map.get(num).add(building);
                building = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(solve(i));
        }

    }

    public static int solve(int start) {

        if (dp[start] != 0) {
            return dp[start];
        }

        for (int index : map.get(start)) {
            dp[start] = Math.max(dp[start], solve(index-1));
        }
        dp[start] += buildings[start];

        return dp[start];
    }
}

