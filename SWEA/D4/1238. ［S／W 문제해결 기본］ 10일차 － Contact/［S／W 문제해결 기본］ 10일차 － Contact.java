import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            Map<Integer, ArrayList<Integer>> map = new HashMap<>();

            for (int i = 0; i < len / 2; i++) {
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());

                if(!map.containsKey(first)){
                    map.put(first, new ArrayList<>());
                }
                map.get(first).add(second);
            }

            sb.append("#").append(tc).append(" ").append(solve(map, start)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(Map<Integer, ArrayList<Integer>> map, int start) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 0));
        Set<Integer> visited = new HashSet<>();

        int max = start;
        int maxCount = 0;

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (!visited.add(curNode.index)) {
                continue;
            }

            if (maxCount < curNode.count) {
                maxCount = curNode.count;
                max = curNode.index;
            } else if (maxCount == curNode.count && max < curNode.index) {
                max = curNode.index;
            }

            if(!map.containsKey(curNode.index)){
                continue;
            }

            for (int nextIndex : map.get(curNode.index)) {
                queue.offer(new Node(nextIndex, curNode.count + 1));
            }
        }

        return max;
    }

    private static class Node {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
