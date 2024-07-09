import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static List<List<Node>> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx <= N; idx++) {
            map.add(new ArrayList<>());
        }

        for (int idx = 0; idx < N - 1; idx++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            long usado = Long.parseLong(st.nextToken());

            map.get(first).add(new Node(second, usado));
            map.get(second).add(new Node(first, usado));
        }

        for (int idx = 0; idx < Q; idx++) {
            st = new StringTokenizer(br.readLine());
            int solve = solve(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(solve).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(long k, int idx) {

        boolean[] visited = new boolean[map.size()];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(idx, Long.MAX_VALUE));
        visited[idx] = true;
        int count = -1;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.usado >= k) {
                count ++;
            }


            for (Node next : map.get(cur.idx)) {
                if(visited[next.idx]) {
                    continue;
                }
                visited[next.idx] = true;
                q.offer(new Node(next.idx, Math.min(cur.usado, next.usado)));
            }
        }

        return count;
    }

    private static class Node{
        int idx;
        long usado;

        public Node(int idx, long usado) {
            this.idx = idx;
            this.usado = usado;
        }
    }

}
