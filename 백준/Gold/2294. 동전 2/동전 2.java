
import java.util.Scanner;

public class Main {

    static int K;
    static int[] dp;
    static int[] coin;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        K = sc.nextInt();
        coin = new int[N];
        dp = new int[K + 1];
        for (int testCase = 0; testCase < N; testCase++) {
            coin[testCase] = sc.nextInt();
        }

        solve();
    }

    public static void solve() {

        dp[0] = 0;

        for (int index = 0; index < coin.length; index++) {
            for (int i = coin[index]; i <= K; i++) {

                if(dp[i] == 0){
                    if(dp[i-coin[index]] != 0) {
                        dp[i] = dp [ i - coin[index]] +1;

                    }else if (i % coin[index] == 0){
                        dp[i] = i / coin[index];
                    }


                } else{
                    if(dp[i-coin[index]] != 0) {
                        dp[i] = Math.min(dp[i], dp[i - coin[index]] + 1);
                    }else if(i % coin[index] == 0){
                        dp[i] = i / coin[index];
                    }


                }
            }
        }

        if (dp[K] == 0) {
            dp[K] = -1;
        }
        System.out.println(dp[K]);

    }
}
