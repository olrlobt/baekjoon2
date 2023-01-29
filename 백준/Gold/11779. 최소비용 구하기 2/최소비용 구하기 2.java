
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static ArrayList<ArrayList<Bus>> gragh = new ArrayList<>();

    static int startPoint;
    static int endPoint;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            gragh.add(new ArrayList<>());
        }

        for (int route = 0; route < M; route++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            gragh.get(start).add(new Bus(end, cost));
        }
        startPoint = sc.nextInt();
        endPoint = sc.nextInt();

        solve();
    }

    public static void solve() {

        PriorityQueue<Bus> pq = new PriorityQueue<>();

        Bus startBus = new Bus(startPoint, 0);
        startBus.coruce = new int[1];
        pq.offer(startBus);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[startPoint] = 0;

        while (!pq.isEmpty()) {

            Bus curBus = pq.poll();
            curBus.coruce[curBus.coruce.length - 1] = curBus.index;

            if (curBus.index == endPoint) {
                System.out.println(curBus.cost);
                System.out.println(curBus.coruce.length);
                for (int i = 0; i < curBus.coruce.length; i++) {
                    System.out.print(curBus.coruce[i] + " ");
                }

                return;
            }

            for (Bus bus : gragh.get(curBus.index)) {

                if (dp[bus.index] > curBus.cost + bus.cost) {
                    dp[bus.index] = curBus.cost + bus.cost;
                    Bus nextBus = new Bus(bus.index, dp[bus.index]);

                    nextBus.coruce = Arrays.copyOf(curBus.coruce, curBus.coruce.length + 1);
                    pq.offer(nextBus);
                }
            }

        }


    }

    public static class Bus implements Comparable<Bus> {

        int index;
        int cost;
        int[] coruce;

        public Bus(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }


        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }
}
