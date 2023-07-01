
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int duration;
    static int[][] subjects;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        duration = sc.nextInt();
        subjects = new int[2][N];

        for (int num = 0; num < N; num++) {
            subjects[0][num] = sc.nextInt();
            subjects[1][num] = sc.nextInt();
        }

        solve();
    }

    public static void solve() {

        int [][] dp = new int[N][duration+1];

        for(int num = 0 ; num < N; num ++){
            for(int time = 0 ; time <= duration; time ++){

                if(num == 0){
                    if(time >= subjects[0][num]){
                        dp[num][time] = subjects[1][num];
                    }
                    continue;
                }
                if(time < subjects[0][num]){
                    dp[num][time] = dp[num-1][time];
                    continue;
                }
                dp[num][time] = Math.max(dp[num - 1][time], subjects[1][num] + dp[num - 1][time - subjects[0][num]]);
            }
        }
        System.out.println(dp[N-1][duration]);
    }
}
