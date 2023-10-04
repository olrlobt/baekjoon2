import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {

            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            boolean[][] map = new boolean[N + 1][N + 1];

            for (int num = 0; num < M; num++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[a][b] = true;
            }
            sb.append("#").append(testCase).append(" ").append(solve(map)).append("\n");

        }
        System.out.println(sb);
    }

    private static long solve(boolean[][] map) {

        int[] count = new int[map.length];
        boolean[] visited = new boolean [map.length];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int start = 1; start < map.length; start++) {

            queue.offer(start);
            Arrays.fill(visited, false);
            visited[start] = true;

            while (!queue.isEmpty()) {

                int cur = queue.poll();

                for (int next = 0; next < map.length; next++) {

                    if(!map[cur][next] || visited[next]){
                        continue;
                    }
                    visited[next] = true;
                    count[start] ++;
                    queue.offer(next);
                }
            }

            queue.offer(start);
            while (!queue.isEmpty()) {

                int cur = queue.poll();

                for (int next = 0; next < map.length; next++) {

                    if(!map[next][cur] || visited[next]){
                        continue;
                    }
                    visited[next] = true;
                    count[start] ++;
                    queue.offer(next);
                }
            }
        }

        return Arrays.stream(count).filter(value -> value == count.length-2).count();
    }
}
