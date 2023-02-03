
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static int N;
    static int M;
    static ArrayList<ArrayList<Computer>> connection = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            connection.add(new ArrayList<>());
            result.add(new ArrayList<>());
        }

        for (int connect = 0; connect < M; connect++) {

            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            connection.get(start).add(new Computer(end, distance));
            connection.get(end).add(new Computer(start, distance));
        }
        solve();
    }

    public static void solve() {

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.offer(new Computer(1, 0));

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        while (!pq.isEmpty()) {

            Computer curCom = pq.poll();

            for (Computer com : connection.get(curCom.index)) {

                if (dp[com.index] > com.distance + curCom.distance) {
                    dp[com.index] = com.distance + curCom.distance;

                    pq.offer(new Computer(com.index, dp[com.index]));

                    result.set(com.index, new ArrayList<>(result.get(curCom.index)));
                    result.get(com.index).add(new int[]{curCom.index ,com.index });
                }
            }
        }

        ArrayList<int []> minConnection = new ArrayList<>();
        for(int i = 0 ; i <= N; i ++){
            for(int[] ccc : result.get(i)){
                minConnection.add(ccc);
            }
        }

        List<int[]> zzz = minConnection.stream().distinct().collect(Collectors.toList());

        System.out.println(zzz.size());
        for(int[] xxx : zzz){
            System.out.println(xxx[0] + " " + xxx[1]);
        }



    }

    private static class Computer implements Comparable<Computer> {

        int index;
        int distance;

        public Computer(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Computer o) {
            return distance - o.distance;
        }
    }
}
