
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int X;
    static ArrayList<ArrayList<Town>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int road = 0; road < M; road++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            map.get(start).add(new Town(end, time));
        }

        int max = 0;

        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }
            max = Math.max(max, solve(i, X) + solve(X, i));
        }
        System.out.println(max);
    }

    public static int solve(int startIndex, int endIndex) {

        PriorityQueue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(startIndex, 0));

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[startIndex] = 0;

        while (!pq.isEmpty()) {

            Town curTown = pq.poll();

            for (Town town : map.get(curTown.index)) {

                if (dp[town.index] > curTown.time + town.time) {
                    dp[town.index] = curTown.time + town.time;
                    pq.offer(new Town(town.index, dp[town.index]));

                }
            }
        }
        return dp[endIndex];
    }

    private static class Town implements Comparable<Town> {

        int index;
        int time;

        public Town(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Town o) {
            return time - o.time;
        }
    }
}
