import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Node[] map = new Node[N + 1];

            for (int num = 0; num <= N; num++) {
                map[num] = new Node(num, null);
            }

            for (int idx = 0; idx < M; idx++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[a].next = new Node(b, map[a].next);
                map[b].next = new Node(a, map[b].next);
            }

            boolean[] visited = new boolean[N + 1];
            int count = 0;
            for (int idx = 1; idx <= N; idx++) {
                if (visited[idx]) {
                    continue;
                }
                visited[idx] = true;
                solve(idx, map, visited);
                count++;
            }
            sb.append("#").append(testCase).append(" ").append(count).append("\n");
        }
        System.out.println(sb);

    }

    private static void solve(int idx, Node[] map, boolean[] visited) {

        for (Node next = map[idx].next; next != null; next = next.next) {

            if (visited[next.idx]) {
                continue;
            }
            visited[next.idx] = true;

            solve(next.idx, map, visited);
        }
    }

    private static class Node {

        int idx;
        Node next;

        public Node(int idx, Node next) {
            this.idx = idx;
            this.next = next;
        }
    }
}
