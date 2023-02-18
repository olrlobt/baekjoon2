
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Crossroad>> map;
    static ArrayList<Integer> endCrossroad;
    static int n;

    static int g;
    static int h;

    static ArrayList<Integer> result;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {

            n = sc.nextInt(); // 교차로 수
            int m = sc.nextInt(); // 도로 수
            int t = sc.nextInt(); // 목적지 수

            int s = sc.nextInt(); // 시작점
            g = sc.nextInt(); // 지난도로1
            h = sc.nextInt(); // 지난도로2

            result = new ArrayList<>();
            endCrossroad = new ArrayList<>();
            map = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                map.add(new ArrayList<>());
            }

            for (int route = 0; route < m; route++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                int distance = sc.nextInt();

                map.get(start).add(new Crossroad(end, distance, 0));
                map.get(end).add(new Crossroad(start, distance, 0));
            }

            for (int endPoint = 0; endPoint < t; endPoint++) {
                endCrossroad.add(sc.nextInt());
            }
            solve(s);
            Collections.sort(result);
            for(int i = 0 ; i < result.size(); i ++){
                System.out.print(result.get(i) + " ");
            }
            System.out.println();
        }
    }

    public static void solve(int start) {

        PriorityQueue<Crossroad> pq = new PriorityQueue<>();
        pq.offer(new Crossroad(start, 0, 0));
        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {

            Crossroad curCrossroad = pq.poll();

            if (visited[curCrossroad.index]) {
                continue;
            }
            visited[curCrossroad.index] = true;

            if (curCrossroad.index == g) {
                curCrossroad.gh++;
            }
            if (curCrossroad.index == h) {
                curCrossroad.gh++;
            }

            if (endCrossroad.contains(curCrossroad.index) && curCrossroad.gh == 2) {
                result.add(curCrossroad.index);
            }

            for (Crossroad crossroad : map.get(curCrossroad.index)) {
                pq.offer(new Crossroad(crossroad.index, curCrossroad.distance + crossroad.distance, curCrossroad.gh));
            }
        }
    }


    private static class Crossroad implements Comparable<Crossroad> {
        int index;
        int distance;
        int gh;

        public Crossroad(int index, int distance, int gh) {
            this.index = index;
            this.distance = distance;
            this.gh = gh;
        }

        @Override
        public int compareTo(Crossroad o) {
            if (distance == o.distance) {
                return o.gh - gh;
            }
            return distance - o.distance;
        }
    }
}
