
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static int[] map;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];
        dp = new int[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        solve();
    }

    public static void solve() {

        int max = 0 ;
        for (int i = 0; i < N; i++) {

            for (int j = i; j >= 0; j--) {

                if (map[j] < map[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if(max < dp[i]){
                max = dp[i];
            }
        }

        System.out.println(max + 1);

        Stack<Integer> stack = new Stack<>();
        for(int i = N-1 ; i >= 0 ; i --){
            if(dp[i] == max){
                stack.push(map[i]);
                max--;
            }
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() +" ");
        }

    }
}
