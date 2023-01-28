import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        solve();
    }

    public static void solve() {

        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.add(new Location(K, 0));
        boolean[] visited = new boolean[K + N + 2];

        while (!pq.isEmpty()) {

            Location location = pq.poll();

            if (location.index == N) {
                System.out.println(location.seconds);
                return;
            }
            if (visited[location.index]) {
                continue;
            }
            visited[location.index] = true;

            if (location.index % 2 == 0) {
                pq.offer(new Location(location.index / 2, location.seconds));
            }

            if(location.index <= N + K){
                pq.offer(new Location(location.index + 1, location.seconds + 1));
            }
            if(location.index > 0){
                pq.offer(new Location(location.index - 1, location.seconds + 1));
            }

        }
    }

    public static class Location implements Comparable<Location> {
        int index;
        int seconds;

        public Location(int index, int seconds) {
            this.index = index;
            this.seconds = seconds;
        }

        @Override
        public int compareTo(Location o) {
            return seconds - o.seconds;
        }
    }
}
