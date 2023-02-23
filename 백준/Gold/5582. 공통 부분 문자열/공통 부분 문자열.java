
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static String N;
    static String M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.next();
        M = sc.next();

        solve();
    }

    public static void solve() {

        int[][] dp = new int[N.length() + 1][M.length() + 1];
        int max = 0 ;
        for (int i = 1; i <= N.length(); i++) {
            for (int j = 1; j <= M.length(); j++) {

                if (N.charAt(i - 1) == M.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(dp[i][j] < max){
                        continue;
                    }
                    max = dp[i][j];

                }


            }
        }

        System.out.println(max);

    }
}
