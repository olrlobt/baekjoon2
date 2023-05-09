import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> map;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new ArrayList<>();
        dp = new int[N][N];

        for (int row = 0; row < N; row++) {
            map.add(new ArrayList<>());
            for (int column = 0; column <= row; column++) {
                map.get(row).add(sc.nextInt());
            }
        }

        System.out.println(solve(0, 0));
    }

    public static int solve(int count, int index) {

        if(count == N ){
            return 0;
        }

        if(dp[count][index] != 0){
            return dp[count][index];
        }

        int cost = map.get(count).get(index);

        cost += Math.max(solve(count+1 , index) , solve(count+1 ,index+1));
         dp[count][index] = cost;
        return cost;
    }
}
