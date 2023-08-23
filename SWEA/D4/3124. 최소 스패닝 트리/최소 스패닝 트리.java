import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Node>> map = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }


            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                map.get(A).add(new Node(B, C));
                map.get(B).add(new Node(A, C));
            }


            sb.append("#").append(tc).append(" ").append(solve(map)).append("\n");
        }
        System.out.println(sb);
    }

    private static long solve(ArrayList<ArrayList<Node>> map) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        boolean[] visited = new boolean[map.size()];
        long cnt = 0 ;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (visited[curNode.index]) {
                continue;
            }
            visited[curNode.index] = true;
            cnt += curNode.dis;

            for (Node nextNode : map.get(curNode.index)) {
                pq.offer(nextNode);
            }
        }
        return cnt;
    }


    private static class Node implements Comparable<Node> {
        int index;
        int dis;

        public Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }
    }


}
