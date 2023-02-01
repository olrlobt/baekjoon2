
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count= 1;

        while (true) {

            N = sc.nextInt();
            if (N == 0) {
                break;
            }

            map = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {

                    map[row][column] = sc.nextInt();
                }
            }

            System.out.println("Problem " + count++ + ": " + solve());
        }


    }

    public static int solve() {
        PriorityQueue<Rupee> pq = new PriorityQueue<>();
        pq.offer(new Rupee(0,0,map[0][0]));

        boolean[][] dp = new boolean[N][N];

        while (!pq.isEmpty()) {

            Rupee rupee = pq.poll();
            if(dp[rupee.y][rupee.x]){
                continue;
            }
            dp[rupee.y][rupee.x] = true;


            if (rupee.y == N - 1 && rupee.x == N - 1) {
                return rupee.rupee;
            }

            for (int i = 0; i < 4; i++) {

                if (rupee.y + dy[i] < 0 || rupee.y + dy[i] >= N || rupee.x + dx[i] < 0 || rupee.x + dx[i] >= N) {
                    continue;
                }
                pq.offer(new Rupee(rupee.y + dy[i] ,rupee.x + dx[i] , rupee.rupee + map[rupee.y+ dy[i]][rupee.x+ dx[i]] ));

            }
        }
        return 0;
    }


    private static class Rupee implements Comparable<Rupee> {

        int x;
        int y;
        int rupee;

        public Rupee(int y, int x, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Rupee o) {
            return rupee - o.rupee;
        }
    }

}