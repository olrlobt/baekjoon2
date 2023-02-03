
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] dp;
    static int[][] result;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        dp = new int[N + 1][N + 1];
        result = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int route = 0; route < M; route++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            dp[start][end] = distance;
            dp[end][start] = distance;
            result[start][end] = end;
            result[end][start] = start;
        }

        solve();
    }

    public static void solve() {

        boolean [][] check = new boolean[N+1][N+1];



        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {

                    if (i == k || j == k || i == j) {
                        continue;
                    }
                    if (dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];

                        if(check[i][k]){
                            result[i][j] = result[i][k];
                        }else{
                            result[i][j] = k;
                        }

                        check[i][j] = true;
                    }
                }
            }
        }



        for(int i = 1 ; i <= N; i ++){
            for(int j = 1 ; j <= N; j ++){

                if(result[i][j] == 0){
                    System.out.print("- ");
                    continue;
                }
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
