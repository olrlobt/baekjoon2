
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] map;
    static boolean[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];
        dp = new boolean[N][N];

        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        setDP();
        StringBuilder sb = new StringBuilder();
        int M = sc.nextInt();
        for (int num = 0; num < M; num++) {

            int start = sc.nextInt();
            int end = sc.nextInt();

            if(dp[start-1][end-1]){
                sb.append("1\n");

            }else{

                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }

    private static void setDP() {

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;  // 길이 1

            if (i != N - 1 && map[i] == map[i + 1]) {  // 길이 2
                dp[i][i + 1] = true;
            }
        }

        for (int length = 2; length < N; length++) {  // 길이 3

            for (int start = 0; start + length < N; start++) {

                if(map[start] == map[start + length] && dp[start+1][start+length-1]){
                    dp[start][start+ length] = true;
                }

            }

        }
    }
}
