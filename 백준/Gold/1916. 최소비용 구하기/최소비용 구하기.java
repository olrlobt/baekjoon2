
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] bus;
    static int startPoint;
    static int endPoint;

    static long[] dp;
    static boolean[] visited;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        bus = new int[N + 1][N + 1];

        for(int i =0; i <= N; i ++){
            Arrays.fill(bus[i],INF);
        }

        for (int busInfo = 0; busInfo < M; busInfo++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            bus[start][end] = Math.min(cost,bus[start][end]);
        }

        startPoint = sc.nextInt();
        endPoint = sc.nextInt();

        solve();

        System.out.println(dp[endPoint]);
    }

    public static void solve() {

        dp = new long[N + 1];
        visited = new boolean[N + 1];
        visited[0] = true;
        //Arrays.fill(dp,INF);

        for (int i = 1; i <= N; i++) {
            dp[i] = bus[startPoint][i];
        }


        visited[startPoint] = true;

        for(int i = 0 ; i < N ; i ++){
            int curIndex = minCostIndex();

            if(curIndex == -1){
                return;
            }


            visited[curIndex] = true;

            for(int j=1; j <= N; j++) {

                if(!visited[j] && dp[j] > bus[curIndex][j] + dp[curIndex]){

                    dp[j] = bus[curIndex][j] +  dp[curIndex];

                }
            }
        }




    }

    private static int minCostIndex() {

        int index = 0;
        long min = INF;
        for (int i = 1; i <= N; i++) {

            if(!visited[i] && dp[i] < min){
                min = dp[i];
                index = i;
            }
        }
        if( min == INF){
            index = -1;
        }

        return index;
    }
}
