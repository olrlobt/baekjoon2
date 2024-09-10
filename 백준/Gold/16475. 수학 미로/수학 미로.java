
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int P;
    static List<List<Node>> map = new ArrayList<>();
    static List<Integer> pits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pits = new ArrayList<>(K);

        for (int i = 0; i < K; i++) {
            pits.add(Integer.parseInt(st.nextToken()));
        }

        for (int idx = 0; idx <= N; idx++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M - L; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            map.get(A).add(new Node(B, C, -1));
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            map.get(A).add(new Node(B, C, 1));
            map.get(B).add(new Node(A, C, 0));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(solve(start, end));
    }

    private static int solve(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.idx == end) {
                return cur.dis;
            }

            for (Node next : map.get(cur.idx)) {

                if (next.able != -1 && cur.able != next.able) {
                    continue;
                }

                int able = cur.able;
                int count = cur.count;
                if (pits.contains(next.idx)) {
                    count++;
                    if (count == P) {
                        count = 0;
                        able ^= 1;
                    }
                }
                pq.offer(new Node(next.idx, cur.dis + next.dis, able, count));
            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int dis;
        int able;
        int count;

        public Node(int idx, int dis, int able, int count) {
            this.idx = idx;
            this.dis = dis;
            this.able = able;
            this.count = count;
        }

        public Node(int idx, int dis, int able) {
            this.idx = idx;
            this.dis = dis;
            this.able = able;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(dis, o.dis);
        }
    }
}
